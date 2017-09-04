/*
 * DigestStream.java
 *
 * Created on August 7, 2005, 6:16 PM
 *
 */

import java.security.*;
import java.io.*;

/**
 * This class send a digest message
 * @author Giscard Fernandes Faria
 */
public class DigestStream {
    
    public static void main(String[] args) {        
        
        try{
        
            MessageDigest sha = MessageDigest.getInstance("SHA");        
            DigestOutputStream dout = new DigestOutputStream(System.out, sha);
            
            dout.write(new String("Giscard Fernandes Faria").getBytes());
            dout.flush();            
           
            byte digest[] = dout.getMessageDigest().digest();            
            System.out.println("\nDigest: " + new String(digest));            
            dout.close();
            
        }//end try
        catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }//end catch
        
    }//end main() method
    
}//End DigestStream class
