/*
 * SourceViewer.java
 *
 * Created on 11 de Setembro de 2005, 18:35
 *
 */

import java.io.*;
import java.net.*;

/**
 * Read a source code from an web resource
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SourceViewer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String urls[] = {"http://www.nec.com.br"};
        args = urls;
        
        if(args.length > 0){
            try{
                /* Open the URL for reading */
                URL u = new URL(args[0]);
                InputStream in = u.openStream();
                
                /* buffer the input to increase performance */
                in = new BufferedInputStream(in);
                
                /* chain the input stream to a reader */
                Reader r = new InputStreamReader(in);
                int c;
                while((c = r.read()) != -1)
                    System.out.print((char)c);
            }//end try
            catch(MalformedURLException muex){
                System.err.println(args[0] + " is not a parseable URL");
            }//end catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//End catch
        }//End if
        
    }//end main() method
    
}//End SourceViewer class