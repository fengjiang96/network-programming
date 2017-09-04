/*
 * LastModified.java
 *
 * Created on 6 de Outubro de 2005, 22:17
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Get time when a URL was last changed
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LastModified {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            try{
                URL u = new URL(args[i]);
                HttpURLConnection http = (HttpURLConnection)u.openConnection();
                http.setRequestMethod("HEAD");
                System.out.println(u + " wast last modified at " + new Date(http.getLastModified()));
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[i] + " is not a URL I understand");
            }//end catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//end catch
        }//end for
        
    }//End main() method
    
}//End LastModified class