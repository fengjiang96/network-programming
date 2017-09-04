/*
 * ProtocolTesterApplet.java
 *
 * Created on 11 de Setembro de 2005, 16:53
 *
 */

import java.awt.*;
import java.net.*;
import javax.swing.*;

/**
 * Test all supported protocol from the browser VM
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ProtocolTesterApplet extends javax.swing.JApplet {
    
    TextArea results = new TextArea();
    
    /**
     * @see javax.swing.JApplet#init()
     */
    public void init(){
        this.setLayout(new BorderLayout());
        this.add("Center", results);
    }//end init() method
    
    /**
     * @see javax.swing.JApplet#start()
     */
    public void start(){
        
        String host = "www.peacefire.org";
        String file = "/bypass/SurfWatch";
        
        String schemes[] = {"http", "https", "ftp", "mailto", "telnet", "file", "ldap", "gopher",
                            "jdbc", "rmi", "jndi", "jar", "doc", "netdoc", "nfs", "verbatim",
                            "finger", "daytime", "systemresource"};
                            
        for(int i = 0; i < schemes.length; i++){
             try{
                 URL u = new URL(schemes[i], host, file);
                 results.append(schemes[i] + " is supported\r\n");                 
             }//end try           
             catch(MalformedURLException ex){
                 results.append(schemes[i] + " is not supported\r\n");
             }//End catch
        }//end for
        
    }//end start() method
    
}//End ProtocolTesterApplet