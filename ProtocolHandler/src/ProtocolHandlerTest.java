/*
 * ConnectTest.java
 *
 * Created on 9 de Outubro de 2005, 20:57
 * 
 */

import java.io.*;
import java.net.*;

/**
 * Test all handler connections
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ProtocolHandlerTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String finger = "finger://rama.poly.edu:79/";
        String daytime = "daytime://tock.usno.navy.mil/";
        
        URL.setURLStreamHandlerFactory(new NewFactory());
        
        try{            
        
            URLConnection fingerConnection = new URL(finger).openConnection();
            BufferedInputStream fingerIn = new BufferedInputStream(fingerConnection.getInputStream());
            
            int fingerC;
            while((fingerC = fingerIn.read()) != -1)
                System.out.print((char)fingerC);
            
            URLConnection daytimeConnection = new URL(daytime).openConnection();
            BufferedInputStream daytimeIn = new BufferedInputStream(daytimeConnection.getInputStream());
            int daytimeC;
            while((daytimeC = daytimeIn.read()) != -1)
                System.out.print((char)daytimeC);
            
        }//end try
        catch(Exception ex){
            System.out.println("Something was got wrong.");
            ex.printStackTrace();
        }//End catch
        
    }//End main() method
    
}//End ConnectTest class