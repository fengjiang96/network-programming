/*
 * UDPEchoServerWithChannels.java
 *
 * Created on 3 de Outubro de 2005, 22:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * A UDP Echo Server based on channels
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPEchoServerWithChannels {
    
    public final static int DEFAULT_PORT = 7;
    public final static int MAX_PACKET_SIZE = 65507;
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = DEFAULT_PORT;
        try{
            port = Integer.parseInt(args[0]);
        }//end try
        catch(Exception ex){}//End catch
        
        try{
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(port);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
            while(true){
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                channel.send(buffer, client);
                buffer.clear();
            }//end while
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        
    }//end main() method
    
}//End UDPEchoServerWithChannels class