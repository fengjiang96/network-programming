/*
 * AllHeaders.java
 *
 * Created on 5 de Outubro de 2005, 22:26
 *
 */

import java.io.*;
import java.net.*;

/**
 * Print the entire HTTP header
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class AllHeaders {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            
            try{
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                for(int j = 1; ; j++){
                    String header = uc.getHeaderField(j);
                    if(header == null)
                        break;
                    System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
                }//end for                    
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[i] + " is not a URL I understand.");
            }//End catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//end catch
            System.out.println();
        }//end for
        
    }//end main() method
    
}//end AllHeaders class