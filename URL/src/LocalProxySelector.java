/*
 * LocalProxySelector.java
 *
 * Created on 11 de Setembro de 2005, 23:10
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Create a local proxy selector
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LocalProxySelector extends ProxySelector {
    
    private List failed = new ArrayList();
    
    /**
     * @see java.net.ProxySelector#select()
     */
    public List<Proxy> select(URI uri){
        
        List<Proxy> result = new ArrayList<Proxy>();
        if(failed.contains(uri) || "http".equalsIgnoreCase(uri.getScheme()))
            result.add(Proxy.NO_PROXY);
        else{
            SocketAddress proxyAddress = new InetSocketAddress("proxy.examples.com", 8000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            result.add(proxy);
        }//end else
        
        return result;
        
    }//End select() method
    
    /**
     * @see java.net.ProxySelector#connectFailed()
     */
    public void connectFailed(URI uri, SocketAddress address, IOException ioe){        
        failed.add(uri);        
    }//End connectionFailed() method
    
}//End LocalProxySelector class
