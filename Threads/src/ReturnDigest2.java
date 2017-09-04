/*
 * ReturnDigest2.java
 *
 * Created on 8 de Setembro de 2005, 21:28
 *
 */

import java.io.*;
import java.security.*;

/**
 * Implements a multi-thread which will calculate the SHA digest for some files
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ReturnDigest2 extends Thread {
    
    private File input;         //represent the file to perform SHA operation    
    private byte[] digest;      //represent the calculated digest
    
    /**
     * Create a new ReturnDigest object
     * @param input A <code>File</code> representing the file to calcula the SHA digest
     */
    public ReturnDigest2(File input){
        
        this.input = input;
        
    }//End ReturnDigest2() constructor
    
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
            
            digest = sha.digest();                                      //get the digest
            
        }//End try
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        catch(NoSuchAlgorithmException nsae){
            System.err.println(nsae);
        }//End catch
        
    }//End run() method
    
    /**
     * Return the calculated digest from file
     * @return A <code>byte[]</code> representing the calculated digest
     */
    public byte[] getDigest(){
        return digest;
    }//end getDigest() method
    
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
                          
        args = files;                                               //set the argument parameter
        
        ReturnDigest2 digests[] = new ReturnDigest2[args.length];
        
        /* for each file */
        for(int i = 0; i < args.length; i++){
            
            File f = new File(path + args[i]);                      //get the file
            digests[i] = new ReturnDigest2(f);                      //create the thread
            digests[i].start();                                     //start the thread
            
        }//End for
        
        /* for each file */
        for(int i = 0; i < args.length; i++){
                    
            StringBuffer result = new StringBuffer(args[i]);        //get the file name
            result.append(": ");
            byte[] digest = digests[i].getDigest();                 //get the digest result
            
            /* mount the print text */
            for(int j = 0; j < digest.length; j++)
                result.append(digest[i] + " ");                    
            System.out.println(result);
            
        }//End for
        
    }//End main() method
    
}//End ReturnDigest2 class