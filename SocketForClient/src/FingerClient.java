/*
 * FingerClient.java
 *
 * Created on 15 de Setembro de 2005, 22:10
 *
 */

import java.io.*;
import java.net.*;

/**
 * A Java command-line finger client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FingerClient {
        
    public final static int DEFAULT_PORT = 79;          //default finger port
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        String hostname = "localhost";
        
        /* get the server hostname */
        try{
            hostname = args[0];
        }//end try
        catch(ArrayIndexOutOfBoundsException ex){
            hostname = "localhost";
        }//end catch
        
        Socket connection = null;                       //socket connection
        
        try{
            connection = new Socket(hostname, DEFAULT_PORT);
            Writer out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
            
            /* send all login user name */
            for(int i = 1; i < args.length; i++)
                out.write(args[i] + " ");
            out.write("\r\n");
            out.flush();
            
            /* get the input stream */
            InputStream raw = connection.getInputStream();
            BufferedInputStream buffer = new BufferedInputStream(raw);
            InputStreamReader in = new InputStreamReader(buffer, "8859_1");
            
            int c;
            while((c = in.read()) != -1)
                if((c >= 32 && c < 127) || c == '\t' || c == '\r' || c =='\n')
                    System.out.write(c);
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        finally{
            try{
                if(connection != null)
                    connection.close();
            }//end try
            catch(IOException ioe){}
        }//end finally
        
    }//End main() method
    
}//end FingerClient class