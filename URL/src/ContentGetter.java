/*
 * ContentGetter.java
 *
 * Created on 11 de Setembro de 2005, 18:46
 *
 */

import java.io.*;
import java.net.*;

/**
 * Get the type of content will be retrieved by an URL
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ContentGetter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        String urls[] = {"http://www.nec.com.br"};
        args = urls;
        
        if(args.length > 0){
            try{
                URL u = new URL(args[0]);
                try{
                    Object o = u.getContent();
                    System.out.println("I got a " + o.getClass().getName());
                }//end try
                catch(IOException ioe){
                    System.err.println(ioe);
                }//End catch
            }//End try
            catch(MalformedURLException mue){
                System.err.println(args[0] + " is not a parseable");
            }//End catch
        }//End if
        
    }//End main() method
    
}//End ContextGetter class
