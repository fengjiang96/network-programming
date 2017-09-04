/*
 * URLSplitter.java
 *
 * Created on 11 de Setembro de 2005, 18:15
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.net.*;

/**
 * Splitter all parts from an URL
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class URLSplitter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String urls[] = {"http://www.ncsa.uiuc.edu/demoweb/html-primer.html#A1.3.3.3"};
        args = urls;
        
        for(int i = 0; i < args.length; i++){
            
            try{
                URL u = new URL(args[i]);
                System.out.println("The URL is " + u);
                System.out.println("The scheme is " + u.getProtocol());
                System.out.println("The user info is " + u.getUserInfo());
                
                String host = u.getHost();
                if(host != null){
                    int atSign = host.indexOf('@');
                    if(atSign != -1)
                        host = host.substring(atSign + 1);
                    System.out.println("The host is " + host);
                }//end if
                else
                    System.out.println("The host is null");
                
                System.out.println("The port is " + u.getPort());
                System.out.println("The path is " + u.getPath());
                System.out.println("The ref is " + u.getRef());
                System.out.println("The query string is " + u.getQuery());                
            }//end try
            catch(MalformedURLException ex){
                System.err.println(args[i] + " is not a URL I understand");
            }//End catch
            System.out.println();
        }//End for
        
    }//End main() method
    
}//End URLSplitter class