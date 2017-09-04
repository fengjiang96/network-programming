/*
 * CompressingStream.java
 *
 * Created on August 7, 2005, 5:01 PM
 *
 */

import java.io.*;
import java.util.zip.*;

/**
 * This class uncompress and compress a zip file
 * @author Giscard Fernandes Faria
 */
public class CompressingStream {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int b = 0;                                                              //bytes reads from file
        String path = "/mnt/slave/Network-Programming/Streams/build/classes/";  //basic file path
        String input = "resource/input.zip";                                    //input compressed file        
        ZipEntry ze = null;                                                     //zip entries
        
        try{
        
            FileInputStream fin = new FileInputStream(path + input);
            ZipInputStream zin = new ZipInputStream(fin);
            
            while((ze = zin.getNextEntry()) != null){
                
                System.out.println("ENTRY: " + ze.getName() + "[" + ze.getSize() + "]");
                FileOutputStream fout = new FileOutputStream(path + "resource/" + ze.getName());
                
                while((b = zin.read()) != -1)
                    fout.write(b);
                
                zin.closeEntry();
                fout.flush();
                fout.close();
                
            }//end while
            
            zin.close();
            
        }//end try
        catch(Exception ex){
            System.out.println("Exception " + ex.getMessage());
            ex.printStackTrace();
        }//end catch
        
    }//End main() method
    
}//End CompressingStream class
