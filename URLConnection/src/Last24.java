/*
 * Last24.java
 *
 * Created on 5 de Outubro de 2005, 23:00
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Set ifModifiedSince to 24 hours prior to now
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class Last24 {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Initialize a Date object with the current date and time */
        Date today = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000;
        
        for(int i = 0; i < args.length; i++){
            try{
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));
                uc.setIfModifiedSince(new Date(today.getTime() - millisecondsPerDay).getTime());
                System.out.println("Will retrieve file if it's modified since " + new Date(uc.getIfModifiedSince()));
                
                InputStream in = new BufferedInputStream(uc.getInputStream());
                Reader r = new InputStreamReader(in);
                
                int c;
                while((c = r.read()) != -1)
                    System.out.print((char)c);
                System.out.println("============== FINISH TIME =============");                
                
            }//end try
            catch(Exception ex){
                System.err.println(ex);
            }//end catch
        }//end for
        
    }//End main() method
    
}//End Last24 class