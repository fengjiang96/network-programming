/*
 * GZipAllFiles.java
 *
 * Created on 8 de Setembro de 2005, 23:08
 *
 */

import java.io.*;
import java.util.*;

/**
 * Implements a program which will pass a set of files to be compressed
 * @author Giscard Fernandes Faria
 */
public class GZipAllFiles {
    
    public final static int THREAD_COUNT = 4;
    private static int filesToBeCompressed = -1;
    
    /**
     * Start the main process
     * @param args A <code>String[]</code> representing the command line parameters
     */
    public static void main(String args[]){
        
        String path = ClassLoader.getSystemResource(".").toString();
        path = path.substring(5);
        
        /* files to parse */
        String fileNames[] = {"/resource/flamerobin.url", "/resource/flamerobin.exe", 
                              "/resource/flamerobin.exe.Manifest", "/resource/msvcp71.dll", 
                              "/resource/msvcr71.dll", "/resource/unins000.dat",
                              "/resource/unins000.exe"};
                          
        args = fileNames;                                       //set the argument parameter
        
        Vector pool = new Vector();                             //pool of tasks
        GZipThread[] threads = new GZipThread[THREAD_COUNT];    //create an array of threads
        
        /* initialize it thread */
        for(int i = 0; i < threads.length; i++){
            threads[i] = new GZipThread(pool);
            threads[i].start();
        }//End for
        
        int totalFiles = 0;                                     //total files to be compressed
        
        /* for each file name */
        for(int i = 0; i < args.length; i++){            
            File f = new File(path + args[i]);
            if(f.exists()){
                if(f.isDirectory()){
                    File[] files = f.listFiles();
                    for(int j = 0; j < files.length; j++){
                        /* don't recurse directories */
                        if(!files[j].isDirectory()){
                            totalFiles++;
                            synchronized(pool){
                                pool.add(0, files[j]);
                                pool.notifyAll();
                            }//end synchronized
                        }//End if
                    }//end for
                }//end if
                else{
                    totalFiles++;
                    synchronized(pool){
                        pool.add(0, f);
                        pool.notifyAll();
                    }//End synchronized
                }//end else
            }//end if
        }//End for
        
        filesToBeCompressed = totalFiles;                       //files already compressed
        
        /* make sure that any waiting thread knows that no more files will be added to the pool */
        for(int i = 0; i < threads.length; i++){
            threads[i].interrupt();
        }//End for
        
    }//End main() method
        
    /**
     * Get the quantity of files to be compressed
     * @return A <code>int</code> representing the quantity of files to be compressed
     */
    public static int getNumberOfFilesToBeCompressed(){
        return filesToBeCompressed;
    }//End getNumberOfFilesToBeCompressed() method
    
}//End GZipAllFiles class