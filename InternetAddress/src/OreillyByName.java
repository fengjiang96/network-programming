/*
 * OreillyByName.java
 *
 * Created on 11 de Setembro de 2005, 10:08
 *
 */

import java.net.*;

/**
 * Get the oreilly IP address based on the hostname
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class OreillyByName {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress address = InetAddress.getByName("www.oreilly.com");
            System.out.println(address);
        }//End try
        catch(UnknownHostException uhe){
            System.out.println("Could not find www.oreilly.com");
        }//End catch
        
    }//End main() method
    
}//End OreillyByName class
