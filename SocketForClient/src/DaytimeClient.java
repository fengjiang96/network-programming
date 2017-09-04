/*
 * DaytimeClient.java
 *
 * Created on 14 de Setembro de 2005, 21:57
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.io.*;
import java.net.*;

/**
 * A daytime protocol client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DaytimeClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname;
        
        /* check the hostname */
        if(args.length > 0)
            hostname = args[0];
        else
            hostname = "time.nist.gov";
        
        try{
            Socket theSocket = new Socket(hostname, 13);
            InputStream timeStream = theSocket.getInputStream();
            StringBuffer time = new StringBuffer();
            int c;
            while((c = timeStream.read()) != -1)
                time.append((char)c);
            String timeString = time.toString().trim();
            System.out.println("It is " + timeString + " at " + hostname);
        }//End try
        catch(UnknownHostException uhe){
            System.err.println(uhe);
        }//End catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        
    }//end main() method
    
}//end DaytimeClient class