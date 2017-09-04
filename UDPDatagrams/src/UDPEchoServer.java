/*
 * UDPEchoServer.java
 *
 * Created on 3 de Outubro de 2005, 22:07
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;

/**
 * A UDP Echo Server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPEchoServer extends UDPServer {
    
    public final static int DEFAULT_PORT = 7;
    
    /**
     * Creates a new FastUDPDiscardServer Object
     */
    public UDPEchoServer() throws SocketException {
        super(DEFAULT_PORT);
    }//End FastUDPDiscardServer() constructor
    
    /**
     * @see UDPServer#respond()
     */
    public void respond(DatagramPacket packet){        
        
        try{
            DatagramPacket outgoing = new DatagramPacket(packet.getData(), packet.getLength(), packet.getAddress(), packet.getPort());
            socket.send(outgoing);            
        }//end try
        catch(IOException ex){
            System.err.println(ex);
        }//End catch
        
    }//End respond() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            UDPServer server = new FastUDPDiscardServer();
            server.start();
        }//end try
        catch(SocketException ex){
            System.err.println(ex);
        }//End catch
        
    }//End main() method
    
}//End UDPEchoServer class