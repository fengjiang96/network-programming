/*
 * HTTPSClient.java
 *
 * Created on 21 de Setembro de 2005, 21:17
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

/**
 * HTTPS Client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class HTTPSClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        /* check the user parameter */
        if(args.length == 0){
            System.out.println("Usage: java HTTPSClient host");
            return;
        }//end if
        
        int port = 443;         //default HTTPS port
        String host = args[0];  //get the host
        
        try{
            SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
            
            /* enable all cipher suites */
            String[] supported = socket.getSupportedCipherSuites();
            socket.setEnabledCipherSuites(supported);
            
            /* Send the https message */
            Writer out = new OutputStreamWriter(socket.getOutputStream());
            out.write("GET http://" + host + "/ HTTP/1.1\r\n");
            out.write("Host: " + host + "\r\n");
            out.write("\r\n");
            out.flush();
            
            /* read response */
            BufferedReader in = new SafeBufferedReader(new InputStreamReader(socket.getInputStream()));
            
            /* read the header */
            String s;
            while(!(s = in.readLine()).equals(""))
                System.out.println(s);
            System.out.println();
            
            /* read the length */
            String contentLength = in.readLine();
            int length = Integer.MAX_VALUE;
            try{
                length = Integer.parseInt(contentLength.trim(), 16);
            }//end try
            catch(NumberFormatException ex){}//End catch
            System.out.println(contentLength);
            
            /* read the content */
            int c;
            int i = 0;
            while((c = in.read()) != -1 && i++ < length)
                System.out.write(c);
            
            System.out.println();
            out.close();
            in.close();
            socket.close();
            
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End HTTPSClient class