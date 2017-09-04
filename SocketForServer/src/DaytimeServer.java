/*
 * DaytimeServer.java
 *
 * Created on 15 de Setembro de 2005, 23:12
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A daytime server
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DaytimeServer {
    
    public final static int DEFAULT_PORT = 13;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = DEFAULT_PORT;
        
        /* get the user data */
        if(args.length > 0){
            try{
                port = Integer.parseInt(args[0]);
                if(port < 0 || port > 65536){
                    System.err.println("Port must between 0 and 65536");
                    return;
                }//end if
            }//end try
            catch(NumberFormatException nfe){}//end catch
        }//end if
        
        try{
            
            ServerSocket server = new ServerSocket(port);
            Socket connection = null;
            
            while(true){
                
                try{
                    connection = server.accept();
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                    connection.close();
                }//end try
                catch(IOException ioe){}//end catch
                finally{
                    try{
                        if(connection != null)
                            connection.close();
                    }//end try
                    catch(IOException ioe){}
                }//end finally
                
            }//end while
            
        }//end try
        catch(IOException ioe){}//end catch        
        
    }//end main() method
    
}//End Daytime class
