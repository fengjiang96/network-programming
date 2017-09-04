/*
 * Handler.java
 *
 * Created on 9 de Outubro de 2005, 20:51
 *

 */

package com.aioba.net.www.protocol.daytime;

import java.io.*;
import java.net.*;
import com.aioba.net.www.protocol.*;

/**
 * The finger handler class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class Handler extends URLStreamHandler {
    
    /**
     * Get the default port to handle the finger connection
     * @return A <code>int</code> representing the default finger port
     */
    public int getDefaultPort(){
        return 13;
    }//End getDefaultPort() method
    
    protected URLConnection openConnection(URL u) throws IOException {
        return new DaytimeURLConnection(u);
    }//end openConnection() method
    
}//End Handler class
