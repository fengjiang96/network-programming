/*
 * SimpleCacheRequest.java
 *
 * Created on 6 de Outubro de 2005, 23:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A basic CacheRequest subclass
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SimpleCacheRequest extends CacheRequest {
    
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    
    /**
     * @see java.net.CacheRequest#getBody()
     */
    public OutputStream getBody() throws IOException{
        return out;
    }//end getBody() method
    
    /**
     * @see java.net.CacheRequest#abort()
     */
    public void abort(){
        out = null;
    }//end abort() method
    
    /**
     * Get all cached data
     * @return A <code>byte[]</code> representing data
     */
    public byte[] getData(){
        if(out == null)
            return null;
        else
            return out.toByteArray();
    }//End getData() method
    
}//end SimpleCacheRequest class