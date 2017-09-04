/*
 * CallbackDigest.java
 *
 * Created on 8 de Setembro de 2005, 21:47
 * 
 */

import java.io.*;
import java.security.*;

/**
 * Implements a multi-thread which will calculate the SHA digest for some files
 * @author Giscard Fernandes Faria
 */
public class CallbackDigest implements Runnable {
    
    private File input;         //represent the file to perform SHA operation
    
    /**
     * Create a new CallbackDigest object
     * @param input A <code>File</code> representing the file to calcula the SHA digest
     */
    public CallbackDigest(File input){
        
        this.input = input;
        
    }//End CallbackDigest() constructor
    
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
            
            CallbackDigest.receiveDigest(digest, input.getName());      //call the callback function
            
        }//End try
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        catch(NoSuchAlgorithmException nsae){
            System.err.println(nsae);
        }//End catch
        
    }//End run() method
    
    /**
     * Callback to receive digest message
     * @param digest A <code>byte[]</code> representing the digest message
     * @param name A <code>String</code> representing the file name
     */
    public static void receiveDigest(byte[] digest, String name){
        
        /* mount the final digest message */
        StringBuffer result = new StringBuffer(name);
        result.append(": ");
        for(int i = 0; i < digest.length; i++)
            result.append(digest[i] + " ");
        System.out.println(result);            
        
    }//end receiveDigest() method
    
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
                          
        args = files;                                       //set the argument parameter
        
        for(int i = 0; i < args.length; i++){
            File f = new File(path + args[i]);              //get the file
            CallbackDigest cb = new CallbackDigest(f);      //create the runnable
            Thread t = new Thread(cb);                      //create the thread
            t.start();                                      //run thread
        }//End for
        
    }//End main() method
    
}//End CallbackDigest class