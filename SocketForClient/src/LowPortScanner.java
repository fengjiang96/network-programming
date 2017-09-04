/*
 * LowPortScanner.java
 *
 * Created on 13 de Setembro de 2005, 22:39
 *
 */

import java.io.*;
import java.net.*;

/**
 * Find out which of the first 1024 ports seem to be hosting TCP servers on a specified
 * host
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LowPortScanner {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String host = "localhost";
        
        /* check if some host are pointed */
        if(args.length > 0)
            host = args[0];
        
        /* for each low port */
        for(int i = 0; i < 1024; i++){
            Socket connection = null;
            try{
                connection = new Socket(host, i);
                System.out.println("There is a server on port " + i + " of " + host);
            }//end try
            catch(UnknownHostException uhe){
                System.err.println(uhe);
                break;
            }//End catch
            catch(IOException ioe){                
                /* must not be a server on this port */
            }//End catch
            finally{
                try{
                    if(connection != null)
                        connection.close();
                }//end try
                catch(IOException ioe){}                
            }//End finally
            
        }//end for
        
    }//End main() method
    
}//end LowPortScanner class
