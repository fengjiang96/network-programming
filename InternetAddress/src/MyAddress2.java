/*
 * MyAddress2.java
 *
 * Created on 11 de Setembro de 2005, 11:24
 *
 */

import java.net.*;

/**
 * Get the IP address and hostname from the local machine
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MyAddress2 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress me = InetAddress.getLocalHost();
            String dottedQuad = me.getHostAddress();
            System.out.println("My Address is " + dottedQuad);
        }//End try
        catch(UnknownHostException uhe){
            System.out.println("I'm sorry. I don't know my own address.");
        }//End catch
        
    }//End main() method
    
}//End MyAddress2 class
