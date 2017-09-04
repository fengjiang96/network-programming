/*
 * JHTTP.java
 *
 * Created on 20 de Setembro de 2005, 23:09
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The JHTTP web server 
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class JHTTP extends Thread{
    
    private File documentRootDirectory;
    private String indexFileName = "index.html";
    private ServerSocket server;
    private int numThreads = 50;
    
    /**
     * Creates a new instance of JHTTP
     * @param documentRootDirectory A <code>File</code> representing the root directory
     * @param port A <code>int</code> representing the port page
     * @param indexFileName A <code>String</code> representing the index page
     * @throws IOException if isn't possible to open the connection
     */
    public JHTTP(File documentRootDirectory, int port, String indexFileName) throws IOException {
        
        if(!documentRootDirectory.isDirectory())
            throw new IOException(documentRootDirectory + "does not exist as a directory");
        this.documentRootDirectory = documentRootDirectory;
        this.indexFileName = indexFileName;
        this.server = new ServerSocket(port);
        
    }//End JHTTP() constructor
    
    public JHTTP(File documentRootDirectory, int port) throws IOException {
        this(documentRootDirectory, port, "index.html");
    }//end JHHTP() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        for(int i = 0; i < numThreads; i++){
            Thread t = new Thread(new RequestProcessor(documentRootDirectory, indexFileName));
            t.start();
        }//end for
        System.out.println("Accepting connections on port " + server.getLocalPort());
        System.out.println("Document Root: " + documentRootDirectory);
        while(true){
            try{
                Socket request = server.accept();
                RequestProcessor.processRequest(request);
            }//end try
            catch(IOException ioe){}
        }//end while
        
    }//End run() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File docRoot = null;
        try{
            docRoot = new File(args[0]);
        }//end try
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Usage: java JHTTP docRoot port indexfile");
            return;
        }//end catch
        
        /* set the port to listen on */
        int port;
        try{
            port = Integer.parseInt(args[1]);
            if(port < 0 || port > 65535)
                port = 80;
        }//end try
        catch(Exception ex){
            port = 80;
        }//end catch
        
        try{
            JHTTP webserver = new JHTTP(docRoot, port);
            webserver.start();
        }//end try
        catch(IOException ioe){
            System.out.println("Server could not start because of an " + ioe.getClass());
            System.out.println(ioe);
        }//end catch
        
    }//end main() method
    
}//End JHTTP class