/*
 * LoggingUDPDiscardServer.java
 *
 * Created on 3 de Outubro de 2005, 22:00
 *
 */

import java.net.*;

/**
 * Log the client packets before discard
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LoggingUDPDiscardServer extends UDPServer {
    
    public final static int DEFAULT_PORT = 9999;
    
    /**
     * Creates a new FastUDPDiscardServer Object
     */
    public LoggingUDPDiscardServer() throws SocketException {
        super(DEFAULT_PORT);
    }//End FastUDPDiscardServer() constructor
    
    /**
     * @see UDPServer#respond()
     */
    public void respond(DatagramPacket packet){
        
        byte[] data = new byte[packet.getLength()];
        System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());
        try{
            String s = new String(data, "8859_1");
            System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);
        }//end try
        catch(java.io.UnsupportedEncodingException ex){        
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
    
}//End LoggingUDPDiscardServer class