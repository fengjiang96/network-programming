package com.aioba.net.www.protocol;
/*
 * FingerURLConnection.java
 *
 * Created on 9 de Outubro de 2005, 18:37
 *
 */

import java.io.*;
import java.net.*;

/**
 * The finger URL connection class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FingerURLConnection extends URLConnection {
    
    private Socket connection = null;
    public final static int DEFAULT_PORT = 79;
    
    /**
     * Creates a new FingerURLConnection object
     * @param u A <code>URLConnection</code> representing the URL connection
     */
    public FingerURLConnection(URL u){
        super(u);
    }//End FingerURLConnection() constructor
    
    /**
     * Get the connection input stream
     * @return A <code>InputStream</code> representing the URL input stream
     * @throws IOException if isn't possible to get the input stream
     */
    public synchronized InputStream getInputStream() throws IOException{
        
        if(!connected)
            this.connect();
        InputStream in = this.connection.getInputStream();
        return in;
        
    }//End getInputStream() method
        
    /**
     * Connect to the server
     * @throws IOException if isn'tp ossible to establish a socket connection
     */
    public synchronized void connect() throws IOException {
        
        if(!connected){
            int port = url.getPort();
            if(port < 1 || port > 65535)
                port = DEFAULT_PORT;
            
            this.connection = new Socket(url.getHost(), port);
            OutputStream out = this.connection.getOutputStream();
            String names = url.getFile();
            
            if(names != null && !names.equals("")){
                names = names.substring(1);
                names = URLDecoder.decode(names);
                byte[] result;
                try{
                    result = names.getBytes("ASCII");                    
                }//end try
                catch(UnsupportedEncodingException uee){
                    result = names.getBytes();
                }//end catch
                out.write(result);
            }//end if
            out.write('\r');
            out.write('\n');
            out.flush();
            this.connected = true;
        }//end if
        
    }//End connect() method
    
}//End FingerURLConnection class