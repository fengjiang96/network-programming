/*
 * SourceViewer3.java
 *
 * Created on 6 de Outubro de 2005, 22:35
 *
 */

import java.io.*;
import java.net.*;

/**
 * A Source Viewer that includes the response code and message
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SourceViewer3 {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            
            try{
                /* Open the URLConnection for reading */
                URL u = new URL(args[i]);
                HttpURLConnection uc = (HttpURLConnection)u.openConnection();
                
                int code = uc.getResponseCode();
                String response = uc.getResponseMessage();
                System.out.println("HTTP/1.x " + code + " " + response);
                
                for(int j = 1; ; j++){
                    String header = uc.getHeaderField(j);
                    String key = uc.getHeaderFieldKey(j);
                    if(header == null || key == null)
                        break;
                    System.out.println(key + ": " + header);
                }//end for
                
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
    
}//End SourceViewer2 class