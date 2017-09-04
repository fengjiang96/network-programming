/*
 * EchoClient.java
 *
 * Created on 14 de Setembro de 2005, 22:35
 *
 */

import java.io.*;
import java.net.*;

/**
 * An echo client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class EchoClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname = "localhost";
        
        /* get the server name */
        if(args.length > 0)
            hostname = args[0];
        
        PrintWriter out = null;
        BufferedReader networkIn = null;
        
        try{
            Socket theSocket = new Socket(hostname, 7);
            networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(theSocket.getOutputStream());
            System.out.println("Connected to the echo server");
            
            /* while echo is succesful */
            while(true){
                String theLine = userIn.readLine();
                if(theLine.equals("."))
                    break;
                out.println(theLine);
                out.flush();
                System.out.println(networkIn.readLine());
            }//end while            
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
            ioe.printStackTrace();
        }//End catch
        finally{
            try{
                if(networkIn != null)
                    networkIn.close();
                if(out != null)
                    out.close();
            }//end try
            catch(IOException ioe){}
        }//end finally        
    }//End main() method
    
}//End EchoClient class