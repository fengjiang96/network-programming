/*
 * UDPServer.java
 *
 * Created on 3 de Outubro de 2005, 21:45
 *
 */

import java.io.*;
import java.net.*;

/**
 * UDP Server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public abstract class UDPServer extends Thread {
    
    private int bufferSize;
    protected DatagramSocket socket;
    
    /**
     * Creates a new UDPServer Object
     * @param port A <code>int</code> representing the server port
     * @param bufferSize A <code>int</code> representing the server buffer size
     * @throws SocketException if isn't possible open the connection
     */
    public UDPServer(int port, int bufferSize) throws SocketException {
        this.bufferSize = bufferSize;
        this.socket = new DatagramSocket(port);        
    }//End UDPServer() constructor
        
    /**
     * Creates a new UDPServer Object
     * @param port A <code>int</code> representing the server port     
     * @throws SocketException if isn't possible open the connection
     */
    public UDPServer(int port) throws SocketException {
        this(port, 8192);
    }//End UDPServer() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run() {
        
        byte[] buffer = new byte[bufferSize];
        while(true){
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            try{
                socket.receive(incoming);
                this.respond(incoming);
            }//end try
            catch(IOException ioe){
                System.err.println(ioe);
            }//end catch
        }//end while
        
    }//End run() method
    
    /**
     * Send an response to the client
     * @param request A <code>DatragramPacket</code> representing the packet to respond
     */
    public abstract void respond(DatagramPacket request);
    
}//end UDPServer class