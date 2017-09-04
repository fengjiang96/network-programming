/*
 * ChargenClient.java
 *
 * Created on 21 de Setembro de 2005, 22:55
 *
 */

import java.nio.*;
import java.net.*;
import java.nio.channels.*;
import java.io.IOException;

/**
 * A channel-based chargen client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ChargenClient {
    
    public static int DEFAULT_PORT = 19;            //default server port
    
    /**
     * @param args A <code>String[]</code> representing the parameters
     */
    public static void main(String args[]){
        
        /* check the user parameters */
        if(args.length == 0){            
            System.out.println("Usage: java ChargenClient host [port]");
            return;
        }//end if
        
        /* get the server port */
        int port;
        try{
            port = Integer.parseInt(args[1]);            
        }//end try
        catch(Exception ex){
            port = DEFAULT_PORT;
        }//end catch
        
        try{
            SocketAddress address = new InetSocketAddress(args[0], port);
            SocketChannel client = SocketChannel.open(address);
            
            ByteBuffer buffer = ByteBuffer.allocate(74);
            WritableByteChannel out = Channels.newChannel(System.out);
            
            /* read all data */
            while(client.read(buffer) != -1){
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }//end while            
        }//end try
        catch(IOException ioe){
            ioe.printStackTrace();
        }//end catch
        
    }//End main() method
    
}//End ChargenClient class