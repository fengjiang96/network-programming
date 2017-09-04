/*
 * DMoz.java
 *
 * Created on 12 de Setembro de 2005, 21:22
 *
 */

import java.io.*;
import java.net.*;

/**
 * Client-side program to interface with DMoz search web site
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DMoz {
    
    /**
     * Execute the program
     * @param args A <code>String</code> representing the paramter arguments
     */
    public static void main(String args[]){
        
        String keys[] = {"giscard", "bebida"};
        args = keys;
        
        String target = "";
        
        /* get all search words */
        for(int i = 0; i < args.length; i++)
            target += args[i] + " ";
        
        target = target.trim();
        QueryString query = new QueryString("search", target);
        
        try{
            URL u = new URL("http://search.dmoz.org/cgi-bin/seracg?" + query);
            InputStream in = new BufferedInputStream(u.openStream());
            InputStreamReader theHTML = new InputStreamReader(in);
            int c;
            while((c = theHTML.read()) != -1){
                System.out.print((char)c);
            }//end while
        }//end try
        catch(MalformedURLException ex){
            System.err.println(ex);
        }//End catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
        
}//End DMoz class