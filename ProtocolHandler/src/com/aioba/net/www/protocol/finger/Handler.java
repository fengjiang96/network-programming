/*
 * FingerHandler.java
 *
 * Created on 9 de Outubro de 2005, 18:54
 *
 */

package com.aioba.net.www.protocol.finger;

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
        return 79;
    }//End getDefaultPort() method
    
    protected URLConnection openConnection(URL u) throws IOException {
        return new FingerURLConnection(u);
    }//end openConnection() method
    
}//End FingerHandler class