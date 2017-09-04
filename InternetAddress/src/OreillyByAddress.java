/*
 * OreillyByAddress.java
 *
 * Created on 11 de Setembro de 2005, 10:12
 *
 */

import java.net.*;

/**
 * Get the oreilly hostname based on the IP address
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class OreillyByAddress {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress address = InetAddress.getByName("208.201.239.37");
            System.out.println(address);
        }//End try
        catch(UnknownHostException uhe){
            System.out.println("Could not find www.oreilly.com");
        }//End catch
        
    }//End main() method
    
}//End OreillyByAddress class
