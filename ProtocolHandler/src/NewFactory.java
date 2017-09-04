/*
 * NewFactory.java
 *
 * Created on 9 de Outubro de 2005, 21:15
 *
 */

import java.net.*;

/**
 * A URLStreamHandlerFactory for finger, daytime
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class NewFactory implements URLStreamHandlerFactory {
    
    /**
     * @see java.net.URLStreamHandlerFactory#()
     */
    public URLStreamHandler createURLStreamHandler(String protocol){
        
        if(protocol.equalsIgnoreCase("finger"))
            return new com.aioba.net.www.protocol.finger.Handler();
        else if(protocol.equalsIgnoreCase("daytime"))
            return new com.aioba.net.www.protocol.daytime.Handler();
        else
            return null;
        
    }//End createURLStreamHandler() method
    
}//end NewFactory class
