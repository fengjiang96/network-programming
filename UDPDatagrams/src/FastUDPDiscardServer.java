/*
 * FastUDPDiscardServer.java
 *
 * Created on 3 de Outubro de 2005, 21:54
 *
 */

import java.net.*;

/**
 * A high-performance UDP discard server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FastUDPDiscardServer extends UDPServer {
    
    public final static int DEFAULT_PORT = 9;
    
    /**
     * Creates a new FastUDPDiscardServer Object
     */
    public FastUDPDiscardServer() throws SocketException {
        super(DEFAULT_PORT);
    }//End FastUDPDiscardServer() constructor
    
    /**
     * @see UDPServer#respond()
     */
    public void respond(DatagramPacket packet){        
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
    
}//End FastUDPDiscardServer class
