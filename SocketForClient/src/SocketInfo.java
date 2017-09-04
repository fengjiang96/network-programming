/*
 * SocketInfo.java
 *
 * Created on 14 de Setembro de 2005, 21:39
 *
 */

import java.io.*;
import java.net.*;

/**
 * Get a socket's information
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SocketInfo {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hosts[] = {"10.0.0.18", "10.0.0.27", "10.0.0.2", "10.51.70.69"};
        args = hosts;
        
        /* for each argument */
        for(int i = 0; i < args.length; i++){
            
            try{
                Socket theSocket = new Socket(args[i], 80);
                System.out.println("Connected to " + theSocket.getInetAddress() + " on port " + 
                        theSocket.getPort() + " from port " + theSocket.getLocalPort() + " of " +
                        theSocket.getLocalAddress());
            }//End try
            catch(UnknownHostException uhe){
                System.err.println("I can't find " + args[i]);
            }//End catch
            catch(SocketException se){
                System.err.println("Could not connect to " + args[i]);
            }//End catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//End catch
            
        }//end for
        
    }//End main() method
    
}//End SocketInfo class