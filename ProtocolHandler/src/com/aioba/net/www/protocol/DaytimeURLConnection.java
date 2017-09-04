/*
 * DaytimeURLConnection.java
 *
 * Created on 9 de Outubro de 2005, 19:12
 *
 */

package com.aioba.net.www.protocol;

import java.io.*;
import java.net.*;

/**
 * DaytimeURLConnection class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DaytimeURLConnection extends URLConnection {
    
    private Socket connection = null;
    public final static int DEFAULT_PORT = 13;
    
    /**
     * Creates a new DaytimeURLConnection object
     * @param u A <code>URL</code> representing the url to connect
     */
    public DaytimeURLConnection(URL u){
        super(u);
    }//End DaytuneURLConnection() constructor
    
    /**
     * Get the connection input stream
     * @return A <code>InputStream</code> representing the URL input stream
     * @throws IOException if isn't possible to get the input stream
     */
    public synchronized InputStream getInputStream() throws IOException {
        
        if(!connected)
            this.connect();
        String header = "<html><head><title>The Time at " + url.getHost() + "</title></head><h1>";
        String footer = "</h1></body></html>";
        InputStream in1 = new ByteArrayInputStream(header.getBytes("8859_1"));
        InputStream in2 = this.connection.getInputStream();
        InputStream in3 = new ByteArrayInputStream(footer.getBytes("8859_1"));
        
        SequenceInputStream result = new SequenceInputStream(in1, in2);
        result = new SequenceInputStream(result, in3);
        return result;
        
    }//End getInputStream() method
    
    /**
     * Get the daytime content type
     * @return A <code>String</code> representing the daytime content type
     */
    public String getContentType(){
        return "text/html";
    }//end getContentType() method
    
    public synchronized void connect() throws IOException {
        
        if(!connected){
            int port = url.getPort();
            if(port <= 0 || port > 65535)
                port = DEFAULT_PORT;
            System.out.println("URL: " + url.getHost() + " PORT: " + port);
            this.connection = new Socket(url.getHost(), port);
            this.connected = true;
        }//End if        
        
    }//end connect() method
    
}//End DaytimeURLConnection class