/*
 * ReverseTest.java
 *
 * Created on 11 de Setembro de 2005, 11:21
 *
 */

import java.net.*;

/**
 * Get the hostname based on IP address
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ReverseTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress ia = InetAddress.getByName("208.201.239.37");
            System.out.println(ia.getHostName());
        }//end try
        catch(Exception ex){
            System.err.println(ex);
        }//End catch
        
    }//End main() method
    
}//End ReverseTest class
