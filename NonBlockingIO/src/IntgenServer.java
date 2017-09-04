/*
 * IntgenServer.java
 *
 * Created on 27 de Setembro de 2005, 21:01
 *
 */

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.util.*;
import java.io.IOException;

/**
 * Intgen Server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class IntgenServer {
    
    public static int DEFAULT_PORT = 1919;
    
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
        }//End catch
        System.out.println("Listening for connections on port " + port);
        
        ServerSocketChannel serverChannel;                                      //socket channel
        Selector selector;
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
        
        /* keep reading the channel */
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
            
            /* while has channel to proccess */
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
                        ByteBuffer output = ByteBuffer.allocate(4);
                        output.putInt(0);
                        output.flip();
                        key2.attach(output);
                    }//end if
                    else if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer output = (ByteBuffer)key.attachment();
                        if(!output.hasRemaining()){
                            output.rewind();
                            int value = output.getInt();
                            output.clear();
                            output.putInt(value + 1);
                            output.flip();
                        }//end if
                        client.write(output);
                    }//End if
                }//end try
                catch(IOException ioe){
                    key.cancel();
                    try{
                        key.channel().close();
                    }//end try
                    catch(IOException ex){}
                }//End catch
                
            }//end while
            
        }//end while
        
    }//End main() method
    
}//End IntgenServer class