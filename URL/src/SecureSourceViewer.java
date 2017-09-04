/*
 * SecureSourceViewer.java
 *
 * Created on 12 de Setembro de 2005, 22:14
 *
 */

import java.io.*;
import java.net.*;

/**
 * Get the site secure source
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SecureSourceViewer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Authenticator.setDefault(new DialogAuthenticator());
        
        String urls[] = {"http://localhost/manager/"};
        args = urls;
        
        for(int i = 0; i < args.length; i++){
            try{
                URL u = new URL(args[i]);
                InputStream in = u.openStream();
                in = new BufferedInputStream(in);
                Reader r = new InputStreamReader(in);
                int c;
                while((c = r.read()) != -1)
                    System.out.print((char)c);
            }//end try
            catch(MalformedURLException mue){
                System.err.println(mue);
            }//end catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//end catch            
        }//end for
        
        System.exit(0);
        
    }//End main() method
    
}//End SecureSourceViewer class