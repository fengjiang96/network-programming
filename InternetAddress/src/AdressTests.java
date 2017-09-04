/*
 * AdressTests.java
 *
 * Created on 11 de Setembro de 2005, 11:27
 *
 */

import java.net.*;

/**
 * Check the IP address type
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class AdressTests {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress ia = InetAddress.getLocalHost();
            
            /* check the address type */
            if(getVersion(ia) == 4)
                System.out.println("Ipv4");
            else if(getVersion(ia) == 6)
                System.out.println("Ipv6");
            else
                System.out.println("Unknow type");
        }//end try
        catch(UnknownHostException uhe){
            System.out.println("Isn't possible to get the localhost");
        }//End catch
        
    }//End main() method
    
    public static int getVersion(InetAddress ia){
        
        byte[] address = ia.getAddress();
        
        /* check the address length */
        if(address.length == 4)
            return 4;
        else if(address.length == 6)
            return 6;
        
        return -1;
        
    }//End getVersion() method
    
}//End AddressTests class