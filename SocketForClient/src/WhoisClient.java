/*
 * WhoisClient.java
 *
 * Created on 15 de Setembro de 2005, 22:32
 *
 */

import java.io.*;
import java.net.*;

/**
 * A command-line whois client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class WhoisClient {
        
    public final static int DEFAULT_PORT = 43;
    public final static String DEFAULT_HOST = "whois.internic.net";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname = DEFAULT_HOST;        
        InetAddress server = null;
        
        /* get the server hostname */
        if(args.length > 0)
            hostname = args[0];
            
        /* get the server ip address */
        try{
            server = InetAddress.getByName(hostname);
        }//end try
        catch(UnknownHostException uhe){
            System.err.println("Error: Could not locate whois server " + server);
            System.err.println("Usage: java WhoisClient <server name>");
            return;
        }//end catch
        
        try{
            /* connect to the server */
            Socket theSocket = new Socket(server, DEFAULT_PORT);
            Writer out = new OutputStreamWriter(theSocket.getOutputStream(), "8859_1");
            for(int i = 0; i < args.length; i++)
                out.write(args[i] + " ");
            out.write("\r\n");
            out.flush();
            
            /* get the server response */
            InputStream in = new BufferedInputStream(theSocket.getInputStream());            
            int c;
            while((c = in.read()) != -1)
                System.out.write(c);
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End WhoisClient class