/*
 * NonBlockingSingleFileHTTPServer.java
 *
 * Created on 27 de Setembro de 2005, 22:19
 *
 */

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.Iterator;
import java.net.*;

/**
 * A non-blocking HTTP server that chunks out the same file
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class NonBlockingSingleFileHTTPServer {
    
    private ByteBuffer contentBuffer;
    private int port = 80;    
    
    /**
     * Creates a new NonBlockingSinfleFileHTTPServer
     * @param data A <code>ByteBuffer</code> representing the data buffer
     * @param encoding A <code>String</code> representing the encoding method
     * @param MIMEType A <code>String</code> representing the MIME header type
     * @param port A <code>int</code> representing the port to connect
     * @throws UnsupportedEncondingException if isn't possible to use the specific encoding
     */
    public NonBlockingSingleFileHTTPServer(ByteBuffer data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException{
        
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" +
                "Server: OneFile 2.0\r\n" +
                "Content-length: " + data.limit() + "\r\n" +
                "Content-type: " + MIMEType + "\r\n\r\n";
        byte[] headerData = header.getBytes("ASCII");
        
        ByteBuffer buffer = ByteBuffer.allocate(data.limit() + headerData.length);
        buffer.put(headerData);
        buffer.put(data);        
        buffer.flip();
        this.contentBuffer = buffer;
        
    }//End NonBlockingSingleFileHTTPServer() constructor
    
    /**
     * Run the server
     * @throws IOException if an I/O error occurrs
     */
    public void run() throws IOException {
        
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverChannel.socket();
        Selector selector = Selector.open();
        InetSocketAddress localPort = new InetSocketAddress(port);
        serverSocket.bind(localPort);
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while(true){
            
            selector.select();
            Iterator keys = selector.selectedKeys().iterator();
            while(keys.hasNext()){
                SelectionKey key = (SelectionKey)keys.next();
                keys.remove();
                
                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        SelectionKey newKey = channel.register(selector, SelectionKey.OP_READ);
                    }//End if
                    else if(key.isWritable()){
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        if(buffer.hasRemaining())
                            channel.write(buffer);
                        else
                            channel.close();
                    }//end if
                    else if(key.isReadable()){
                        /* Don't bother trying to parse the HTTP header. Just read something */
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(4096);
                        channel.read(buffer);
                        /* Switch channel to write-only mode */                        
                        key.interestOps(SelectionKey.OP_WRITE);
                        key.attach(contentBuffer.duplicate());                        
                    }//End if
                }//end try
                catch(IOException ioe){
                    key.cancel();
                    try{
                        key.channel().close();
                    }//end try
                    catch(IOException ex){}
                }//end catch
                
            }//end while
            
        }//End while
        
    }//End run() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.out.println("Usage: java NonBlockingSingleFileHTTPServer file port encoding");
            return;
        }//end if
        
        try{
            String contentType = "text/plain";
            if(args[0].endsWith(".html") || args[0].endsWith(".htm"))
                contentType = "text/html";
            
            FileInputStream fin = new FileInputStream(args[0]);
            FileChannel in = fin.getChannel();
            ByteBuffer input = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
            
            /* set the port to listen on */
            int port;
            try{
                port = Integer.parseInt(args[1]);
                if(port < 1 || port > 65535)
                    port = 80;
            }//end try
            catch(Exception ex){
                port = 80;
            }//end catch
            
            String encoding = "ASCII";
            if(args.length > 2)
                encoding = args[2];
            
            NonBlockingSingleFileHTTPServer server = new NonBlockingSingleFileHTTPServer(input, encoding, contentType, port);
            server.run();            
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
            System.err.println(ex);
        }//End catch
        
    }//End main() method
    
}//End NonBlockingSingleFileHTTPServer class