/*
 * UDPPortScanner.java
 *
 * Created on 29 de Setembro de 2005, 21:46
 * 
 */

import java.net.*;

/**
 * Look for local UDP ports
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPPortScanner {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Searching for all ports */
        for(int port = 0; port <= 65535; port++){
            try{
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            }//end try
            catch(SocketException se){
                System.out.println("There is a server on port " + port + ".");
            }//End catch
        }//end for
        
    }//End main() method
    
}//end UDPPortScanner class