/*
 * SourceViewer.java
 *
 * Created on 5 de Outubro de 2005, 21:12
 *
 */

import java.io.*;
import java.net.*;

/**
 * Download a Web page with a URL-Connection
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SourceViewer {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length > 0){
            
            try{
                /* Open the URLConnection for reading */
                URL u = new URL(args[0]);
                URLConnection uc = u.openConnection();
                InputStream raw = uc.getInputStream();
                InputStream buffer = new BufferedInputStream(raw);
                
                /* Chain the input stream to a reader */
                Reader r = new InputStreamReader(buffer);
                int c;
                while((c = r.read()) != -1)
                    System.out.print((char)c);
                
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[0] + " is not a parseable URL");
            }//end catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//End catch
            
        }//end if
        
    }//End main() method
    
}//End SourceViewer class