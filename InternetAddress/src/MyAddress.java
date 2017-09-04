/*
 * MyAddress.java
 *
 * Created on 11 de Setembro de 2005, 10:25
 *
 */

import java.net.*;

/**
 * Get the IP address and hostname from the local machine
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MyAddress {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
        }//End try
        catch(UnknownHostException uhe){
            System.out.println("Could not find this computer's address");
        }//End catch
        
    }//End main() method
    
}//End MyAddress class
