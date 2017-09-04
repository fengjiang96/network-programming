/*
 * HeaderViewer.java
 *
 * Created on 5 de Outubro de 2005, 22:15
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Return the hedaer
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class HeaderViewer {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            
            try{
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                System.out.println("CONTENT TYPE    : " + uc.getContentType());
                System.out.println("CONTENT ENCODING: " + uc.getContentEncoding());
                System.out.println("DATE            : " + new Date(uc.getDate()));
                System.out.println("LAST MODIFIED   : " + new Date(uc.getLastModified()));
                System.out.println("EXPIRE DATE     : " + new Date(uc.getExpiration()));
                System.out.println("CONTENT LENGTH  : " + uc.getContentLength());
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[i] + " is not a URL I understand");
            }//End catch
            catch(IOException ioe){
                System.out.println();
            }//End catch
            
        }//end for
        
    }//End main() method
    
}//End HeaderViewer