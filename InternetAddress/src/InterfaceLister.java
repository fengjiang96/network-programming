import java.net.NetworkInterface;
/*
 * InterfaceLister.java
 *
 * Created on 11 de Setembro de 2005, 13:52
 * 
 */

import java.net.*;
import java.util.*;

/**
 * Get all Network Card Interfaces
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class InterfaceLister {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()){
            NetworkInterface ni = (NetworkInterface)interfaces.nextElement();
            System.out.println(ni);
        }//End while
        
    }//End main() method
    
}//End InterfaceLister class
