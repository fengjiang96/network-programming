/*
 * ChargenServer.java
 *
 * Created on 22 de Setembro de 2005, 21:44
 *
 */

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;

/**
 * A non-blocking chargen server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ChargenServer {
    
    public static int DEFAULT_PORT = 19;        //server default port
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        int port;                               //server port
        try{
            port = Integer.parseInt(args[0]);
        }//end try
        catch(Exception ex){
            port = DEFAULT_PORT;
        }//end catch
        System.out.println("Listening for connections on port " + port);
        
        /* set all print ASCII */
        byte[] rotation = new byte[95 * 2];
        for(byte i = ' '; i <= '~'; i++){
            rotation[i - ' '] = i;
            rotation[i + 95 - ' '] = i;
        }//end for
        
        ServerSocketChannel serverChannel;      //server socket channel
        Selector selector;                      //channel selector
        try{
            serverChannel = ServerSocketChannel.open();
            ServerSocket ss = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            ss.bind(address);
            serverChannel.configureBlocking(false);
            selector = Selector.open();            
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        }//end try
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
            }//end catch
            
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
                        SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
                        ByteBuffer buffer = ByteBuffer.allocate(74);
                        buffer.put(rotation, 0, 72);
                        buffer.put((byte)'\r');
                        buffer.put((byte)'\n');
                        buffer.flip();                        
                        key2.attach(buffer);
                    }//end if
                    else if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        if(!buffer.hasRemaining()){
                            buffer.rewind();
                            int first = buffer.get();
                            buffer.rewind();
                            int position = first - ' ' + 1;
                            buffer.put(rotation, position, 72);
                            buffer.put((byte)'\r');
                            buffer.put((byte)'\n');
                            buffer.flip();                                    
                        }//end if
                        client.write(buffer);
                    }//end if
                }//end try
                catch(IOException ioe){
                    key.cancel();
                    try{
                        key.channel().close();
                    }//end try
                    catch(IOException ioex){}
                }//End catch
            }//end while
            
        }//end while
        
    }//end main() method
    
}//End ChargenServer class
