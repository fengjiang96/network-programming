/*
 * BinaryServer.java
 *
 * Created on 5 de Outubro de 2005, 21:47
 *
 */

import java.io.*;
import java.net.*;

/**
 * Downloading a binary file from a web site and saving it to disk
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class BinaryServer {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            
            try{
                URL root = new URL(args[i]);
                saveBinaryFile(root);
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[i] + " is not URL I understand.");
            }//end catch
            catch(IOException ioe){
                System.err.println(ioe);
            }//End catch
            
        }//end for
        
    }//End main() method
    
    /**
     * Save the gotted file from URL into a local file
     * @param u A <code>URL</code> representing the file url
     * @throws IOException if isn't possible read or save the file
     */
    public static void saveBinaryFile(URL u) throws IOException {
        
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        
        if(contentType.startsWith("text/") || contentLength == -1)
            throw new IOException("This is not a binary file.");
        
        InputStream raw = uc.getInputStream();
        InputStream in = new BufferedInputStream(raw);
        
        byte[] data = new byte[contentLength];        
        int bytesRead = 0;
        int offset = 0;
        while(offset < contentLength){
            bytesRead = in.read(data, offset, data.length - offset);
            if(bytesRead == -1)
                break;
            offset += bytesRead;
        }//end while
        in.close();
        
        if(offset != contentLength)
            throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
        
        String filename = u.getFile();
        filename = filename.substring(filename.lastIndexOf('/') + 1);
        FileOutputStream fout = new FileOutputStream(filename);
        fout.write(data);
        fout.flush();
        fout.close();
        
    }//End savedBinaryFile() method
    
}//End BinaryServer class