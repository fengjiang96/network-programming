/*
 * UDPDiscardServer.java
 *
 * Created on 29 de Setembro de 2005, 22:10
 *
 */

import java.net.*;
import java.io.*;

/**
 * UDP Discard Server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPDiscardServer {
        
    public final static int DEFAULT_PORT = 9;
    public final static int MAX_PACKET_SIZE = 65507;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = DEFAULT_PORT;
        byte[] buffer = new byte[MAX_PACKET_SIZE];
        
        try{
            port = Integer.parseInt(args[0]);
        }//end try
        catch(Exception ex){}//end catch
        
        try{
            DatagramSocket server = new DatagramSocket(port);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while(true){
                try{
                    server.receive(packet);
                    String s = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);
                    packet.setLength(buffer.length);
                }//end try
                catch(IOException ioe){
                    System.err.println(ioe);
                }//end catch
            }//end while
        }//end try
        catch(SocketException se){
            System.err.println(se);
        }//End catch
        
    }//End main() method
    
}//End UDPDiscardServer class