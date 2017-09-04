/*
 * URLEquality.java
 *
 * Created on 11 de Setembro de 2005, 19:39
 *
 */

import java.net.*;

/**
 * Check if two URLs are equals
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class URLEquality {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            URL ibiblio = new URL("http://www.ibiblio.org/");
            URL metalab = new URL("http://metalab.unc.edu/");
            
            /* check if the url are equals */
            if(ibiblio.equals(metalab))
                System.out.println(ibiblio + " is the same as " + metalab);
            else
                System.out.println(ibiblio + " is not the same " + metalab);
            
        }//end try
        catch(MalformedURLException mue){
            System.err.println(mue);
        }//End catch
        
    }//End main() method
    
}//End URLEquality class