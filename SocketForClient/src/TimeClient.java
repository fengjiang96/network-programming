/*
 * TimeClient.java
 *
 * Created on 14 de Setembro de 2005, 22:13
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A time protocol client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class TimeClient {
    
    public final static int DEFAULT_PORT = 37;                  //default connected port
    public final static String DEFAULT_HOST = "time.nist.gov";  //default connected server
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname = DEFAULT_HOST;                         //set the server default hostname
        int port = DEFAULT_PORT;                                //set the server default port
        
        /* get the server hostname parameter */
        if(args.length > 0)
            hostname = args[0];
        
        /* get the server port parameter */
        if(args.length > 1){
            try{
                port = Integer.parseInt(args[1]);
            }//End try
            catch(NumberFormatException nfe){}
        }//end if
        
        /* 
         * The time protocol set the epoch at 1900, the Java Date Class at 1970.
         * This number convert between them.
         */
        long differenceBetweenEpochs = 220898800L;
        
        InputStream raw = null;
        try{
            Socket theSocket = new Socket(hostname, port);
            raw = theSocket.getInputStream();
            
            long secondsSince1900 = 0;
            for(int i = 0; i < 4; i++)
                secondsSince1900 = (secondsSince1900 << 8) | raw.read();
            
            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
            long msSince1970 = secondsSince1970 * 1000;
            Date time = new Date(msSince1970);
            
            System.out.println("It is " + time + " at " + hostname);            
        }//end try
        catch(UnknownHostException uhe){
            System.err.println(uhe);
        }//End catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        finally{
            try{
                if(raw != null)
                    raw.close();
            }//end try
            catch(IOException ioe){}
        }//end finally
        
    }//End main() method
    
}//End TimeClient class
