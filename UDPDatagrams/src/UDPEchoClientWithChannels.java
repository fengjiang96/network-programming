/*
 * UDPEchoClientWithChannels.java
 *
 * Created on 3 de Outubro de 2005, 22:51
 *
 */

import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;
import java.nio.channels.*;

/**
 * A UDP echo client based on channels
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPEchoClientWithChannels {
    
    public final static int DEFAULT_PORT = 7;
    public final static int LIMIT = 100;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = DEFAULT_PORT;
        try{
            port = Integer.parseInt(args[1]);
        }//end try
        catch(Exception ex){}
        
        SocketAddress remote;
        try{
            remote = new InetSocketAddress(args[0], port);
        }//end try
        catch(Exception ex){
            System.err.println("Usage: java UDPEchoClientWithChannels host [port]");
            return;
        }//end catch
        
        try{
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(remote);
            
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            
            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n = 0;
            int numbersRead = 0;
            while(true){
                selector.select(60000);
                Set readyKeys = selector.selectedKeys();
                if(readyKeys.isEmpty() && n == LIMIT){
                    break;
                }//end if
                else{
                    Iterator iterator = readyKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey key = (SelectionKey)iterator.next();
                        iterator.remove();
                        if(key.isReadable()){
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read: " + echo);
                            numbersRead++;
                        }//end if
                        if(key.isWritable()){
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote: " + n);
                            n++;
                        }//end if
                        if(n == LIMIT){
                            key.interestOps(SelectionKey.OP_READ);
                        }//end if
                    }//end while
                }//End else
            }//end while
            System.out.println("Echoed " + numbersRead + " out of " + LIMIT + " sent");
            System.out.println("Success rate: " + 100.0 * numbersRead / LIMIT + "%");
            
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End UDPEchoClientWithChannels class