/*
 * IPCharacteristics.java
 *
 * Created on 11 de Setembro de 2005, 11:44
 *
 */

import java.net.*;

/**
 * Get all features from IP address
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class IPCharacteristics {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname = "www.oreilly.com";                            //hostname to gather information
        
        try{
            InetAddress address = InetAddress.getByName(hostname);      //get the host name
            
            /* check if the address is a wildcard */
            if(address.isAnyLocalAddress())
                System.out.println(address + " is a wildcard address.");
            
            /* check if the address is a loopback address */
            if(address.isLoopbackAddress())
                System.out.println(address + " is a loopback address.");
            
            /* check if the address is a lonk-local address (only to IPv6) */
            if(address.isLinkLocalAddress())
                System.out.println(address + " is a site-local address.");
            
            /* check if the address is a site-local address (only to IPv6) */
            if(address.isSiteLocalAddress())
                System.out.println(address + " is a site-local address.");
            
            /* check if the address is a multicast address */
            if(address.isMulticastAddress())
                System.out.println(address + " is a global multicas address.");
            
            /* check if the address is a organization multicast address (only yo IPv6) */
            if(address.isMCOrgLocal())
                System.out.println(address + " is a organization multicast address.");
            
            /* check if the address is a site multicast address (only to IPv6) */
            if(address.isMCSiteLocal())
                System.out.println(address + " is a site multicast address.");
            
            /* check if the address is a subnet wide multicast address */
            if(address.isMCLinkLocal())
                System.out.println(address + " is a subnet wide multicas address type.");
            
            /* check if the address is interface-local address */
            if(address.isMCNodeLocal())
                System.out.println(address + " is an interface-local multicast address.");
            
        }//End try
        catch(UnknownHostException uhe){
            System.err.println("Could not resolve " + hostname);
        }//End catch
        
    }//End main() method
    
}//End IPCharacteristics class