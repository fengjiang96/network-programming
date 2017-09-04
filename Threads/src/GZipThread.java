/*
 * GZipThread.java
 *
 * Created on 8 de Setembro de 2005, 22:48
 *
 */

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Implements a Thread which can compress a file to GZip format
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class GZipThread extends Thread {
    
    private List pool;                      //represent the pool of tasks
    private static int filesCompressed;     //quantity of files compressed
    
    /**
     * Creates a new GZipThread object
     * @param pool A <code>List</code> representing the pool of tasks
     */
    public GZipThread(List pool){
        this.pool = pool;
    }//End GZipThread() constructor
    
    /**
     * Increments the quantity of compressed file
     */
    private static synchronized void incrementFilesCompressed(){
        filesCompressed++;
    }//End incrementFilesCompressed() method
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        /* run thread until compress all files */
        while(filesCompressed != GZipAllFiles.getNumberOfFilesToBeCompressed()){            
            File input = null;          //file to compress            
            /* synchronized access to pool tasks */
            synchronized(pool){                
                while(pool.isEmpty()){                    
                    /* check if all files are compressed */
                    if(filesCompressed == GZipAllFiles.getNumberOfFilesToBeCompressed()){
                        System.out.println("Thread ending");
                        return;
                    }//end if
                    
                    /* Stop the pool to receive more tasks... */
                    try{
                        //System.out.println(this.getName() + " was inside try wait()");
                        pool.wait();
                    }//end try
                    catch(InterruptedException ie){}
                }//end while                
                input = (File)pool.remove(pool.size() - 1);             //get the first-in task
                incrementFilesCompressed();                             //increment the compressed files
                
            }//End synchronized
            
            /* don't compress an already compressed file */
            if(!input.getName().endsWith(".gz")){
                try{
                    InputStream in = new FileInputStream(input);        //get the file stream
                    in = new BufferedInputStream(in);                   //buffered the stream
                    
                    /* create the compressed file */
                    File output = new File(input.getParent(), input.getName() + ".gz");
                    
                    /* Don't overwrite an existing file */
                    if(!output.exists()){
                        OutputStream out = new FileOutputStream(output);    //get the file output stream
                        out = new GZIPOutputStream(out);                    //get the zip stream
                        out = new BufferedOutputStream(out);                //buffered the output stream
                        
                        /* copy the file */
                        int b;
                        while((b = in.read()) != -1)
                            out.write(b);
                        out.flush();
                        
                        /* close the files */
                        out.close();
                        in.close();
                        
                    }//End if
                }//end try
                catch(IOException ioe){
                    System.err.println(ioe);
                }//End catch
            }//End if            
        }//End while        
    }//End run() method
    
}//End GZipThread class
