/*
 * EchoServer.java
 *
 * Created on 27 de Setembro de 2005, 21:51
 *
 */

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;

/**
 * Echo Server
 * @author Giscrad Fernandes Faria
 * @version 1.0
 */
public class EchoServer {
    
    public static int DEFAULT_PORT = 7;    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port;
        try{
            port = Integer.parseInt(args[0]);
        }//end try
        catch(Exception ex){
            port = DEFAULT_PORT;
        }//end catch
        System.out.println("Listening for connection on port " + port);
        
        ServerSocketChannel serverChannel;
        Selector selector;
        try{
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);            
        }//End try
        catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }//End catch
        
        while(true){
            
            try{
                selector.select();
            }//end try
            catch(IOException ioe){
                ioe.printStackTrace();
                break;
            }//End catch
            
            Set readyKeys = selector.selectedKeys();
            Iterator iterator = readyKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = (SelectionKey)iterator.next();
                iterator.remove();
                
                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel)key.channel();
                        SocketChannel client = server.accept();
                        System.out.println("Accepted connection from " + client);
                        client.configureBlocking(false);
                        SelectionKey clientKey = client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ);                        
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        clientKey.attach(buffer);
                    }//end if
                    else if(key.isReadable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer output = (ByteBuffer)key.attachment();
                        client.read(output);
                    }//end if
                    else if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer output = (ByteBuffer)key.attachment();
                        output.flip();
                        client.write(output);
                        output.compact();
                    }//end if
                }//end try
                catch(IOException ioe){
                    key.cancel();
                    try{
                        key.channel().close();                        
                    }//end try
                    catch(IOException ex){}                        
                }//End catch
                
            }//End while()
            
        }//end while
        
    }//end main() method
    
}//End EchoServer class
