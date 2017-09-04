/*
 * EncodingAwareSourceViewer.java
 *
 * Created on 5 de Outubro de 2005, 21:26
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;

/**
 * Download a Web page with the correct character set
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class EncodingAwareSourceViewer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            
            try{
                /* Open the URLConnection for reading */
                String encoding = "ISO-8859-1";
                URL u = new URL(args[i]);
                URLConnection uc = u.openConnection();
                String contentType = uc.getContentType();
                                
                int encodingStart = contentType.indexOf("charset=");
                if(encodingStart != -1)
                    encoding = contentType.substring(encodingStart + 8);
                
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
            
        }//end for
        
    }//End main() method
    
}//End EncodingAwareSourceViewer class