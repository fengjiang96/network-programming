/*
 * IntgenClient.java
 *
 * Created on 27 de Setembro de 2005, 21:33
 *
 */

import java.nio.*;
import java.nio.channels.*;
import java.net.*;
import java.io.IOException;

/**
 * Intgen Client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class IntgenClient {
    
    public static int DEFAULT_PORT = 1919;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* check the port parameter */
        if(args.length == 0){
            System.out.println("Usage: java IntgenClient host [port]");
        }//end if
        
        int port;
        try{
            port = Integer.parseInt(args[1]);
        }//end try
        catch(Exception ex){
            port = DEFAULT_PORT;
        }//End catch
        
        try{
            SocketAddress address = new InetSocketAddress(args[0], port);
            SocketChannel client = SocketChannel.open(address);
            ByteBuffer buffer = ByteBuffer.allocate(4);
            IntBuffer view = buffer.asIntBuffer();
            
            for(int expected = 0; ; expected++){
                client.read(buffer);
                int actual = view.get();
                buffer.clear();
                view.rewind();            
            
                if(actual != expected){
                    System.err.println("Expected " + expected + " was " + actual);
                    break;
                }//end if
                System.out.println(actual);
            }//End for
        }//end try
        catch(IOException ioe){
            ioe.printStackTrace();
        }//end catch
        
    }//End main() method
    
}//End IntgenClient class