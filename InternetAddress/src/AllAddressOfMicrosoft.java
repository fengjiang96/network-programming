/*
 * AllAddressOfMicrosoft.java
 *
 * Created on 11 de Setembro de 2005, 10:17
 *
 */

import java.net.*;

/**
 * Get all IP address binding to microsoft hostname
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class AllAddressOfMicrosoft {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress[] addresses = InetAddress.getAllByName("www.microsoft.com");
            for(int i = 0; i < addresses.length; i++)
                System.out.println(addresses[i]);
        }//end try
        catch(UnknownHostException uhe){
            System.out.println("Could not find www.microsoft.com");
        }//end catch
        
    }//End main() method
    
}//End AllAddressOfMicrosoft class
