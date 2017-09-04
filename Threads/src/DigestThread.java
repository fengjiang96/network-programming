/*
 * DigestThread.java
 *
 * Created on 7 de Setembro de 2005, 22:53
 *
 */

import java.io.*;
import java.security.*;

/**
 * Implements a multi-thread which will calculate the SHA digest for some files
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DigestThread extends Thread {
    
    private File input;         //represent the file to perform SHA operation    
    
    /**
     * Create a new DigestThread object
     * @param input A <code>File</code> representing the file to calcula the SHA digest
     */
    public DigestThread(File input){
        
        this.input = input;
        
    }//End DigestThread() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        try{
            FileInputStream in = new FileInputStream(input);            //open the stream
            MessageDigest sha = MessageDigest.getInstance("SHA");       //create sha digest
            DigestInputStream din = new DigestInputStream(in, sha);     //create the digest stream
            
            /* read the whole file */
            int b;
            while((b = din.read()) != -1);
            din.close();
            
            byte[] digest = sha.digest();                               //get the digest
            
            /* mount the final digest message */
            StringBuffer result = new StringBuffer(input.toString());
            result.append(": ");
            for(int i = 0; i < digest.length; i++)
                result.append(digest[i] + " ");
            System.out.println(result);            
        }//End try
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        catch(NoSuchAlgorithmException nsae){
            System.err.println(nsae);
        }//End catch
        
    }//End run() method
    
    /**
     * Start the main process
     * @param args A <code>String[]</code> representing the command line parameters
     */
    public static void main(String args[]){
        
        String path = ClassLoader.getSystemResource(".").toString();
        path = path.substring(5);
        
        /* files to parse */
        String files[] = {"/resource/flamerobin.url", "/resource/flamerobin.exe", 
                          "/resource/flamerobin.exe.Manifest", "/resource/msvcp71.dll", 
                          "/resource/msvcr71.dll", "/resource/unins000.dat",
                          "/resource/unins000.exe"};
                          
        args = files;                               //set the argument parameter
        
        for(int i = 0; i < args.length; i++){
            File f = new File(path + args[i]);      //get the file
            Thread t = new DigestThread(f);         //create the thread
            t.start();                              //run thread
        }//End for
        
    }//End main() method
    
}//End DigestThread class
