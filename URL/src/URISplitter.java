/*
 * URISplitter.java
 *
 * Created on 11 de Setembro de 2005, 22:51
 *
 */

import java.net.*;

/**
 * Splitter a URI into parts
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class URISplitter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String uris[] = {"http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hbc", 
                         "tel:+1-800-9988-9938", "urn:isbn:1-565-92870-9"};
                       
        args = uris;
        
        for(int i = 0; i < args.length; i++){
            try{
                URI u = new URI(args[i]);
                System.out.println("The URI is " + u);
                if(u.isOpaque()){
                    System.out.println("This is an opaque URI");
                    System.out.println("The scheme is " + u.getScheme());
                    System.out.println("The scheme specific part is " + u.getSchemeSpecificPart());
                    System.out.println("The fragment ID is " + u.getFragment());
                }//end if
                else{
                    System.out.println("This is a hierarchical URI");
                    System.out.println("The scheme is " + u.getScheme());
                    try{
                        u = u.parseServerAuthority();
                        System.out.println("The host is " + u.getUserInfo());
                        System.out.println("The user info is " + u.getUserInfo());
                        System.out.println("The port is " + u.getPort());
                    }//end try
                    catch(URISyntaxException ex){
                        System.out.println("The authority is " + u.getAuthority());
                    }//End catch
                    System.out.println("The path is " + u.getPath());
                    System.out.println("The query string is " + u.getQuery());
                    System.out.println("The fragment ID is " + u.getFragment());
                }//end else
            }//end try
            catch(URISyntaxException ex){
                System.err.println(args[i] + " does not seem to be a URI");
            }//End catch
            System.out.println();
        }//End for
        
    }//end main() method
    
}//End URISplitter class