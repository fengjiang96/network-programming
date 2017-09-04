/*
 * RequestProcessor.java
 *
 * Created on 20 de Setembro de 2005, 22:23
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Thread pool that handles HTTP request
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class RequestProcessor implements Runnable {
    
    private static List pool = new LinkedList();
    private File documentRootDirectory;
    private String indexFileName = "index.html";
    
    /**
     * Creates a new RequestProcessor Object
     * @param documentRootDirectory A <code>File</code> representing the root directory
     * @param indexFileName A <code>String</code> representing the index file name
     */
    public RequestProcessor(File documentRootDirectory, String indexFileName){
        
        if(documentRootDirectory.isFile())
            throw new IllegalArgumentException("documentRootDirectory MUST be a directory, not a file");
        
        try{
            this.documentRootDirectory = documentRootDirectory.getCanonicalFile();
        }//end try
        catch(IOException ioe){}
        
        /* check the index file name */
        if(indexFileName != null)
            this.indexFileName = indexFileName;
        
    }//End RequestProcessor() constructor
    
    /**
     * Process the client request
     * @param request A <code>Socket</code> representing the Socket request connection
     */
    public static void processRequest(Socket request){
        
        /* synchronize the access to the pool */
        synchronized(pool){
            pool.add(pool.size(), request);
            pool.notifyAll();
        }//end synchronized
        
    }//End processRequest() method
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        /* for security checks */
        String root = documentRootDirectory.getPath();
        
        while(true){
            Socket connection;
            synchronized(pool){
                while(pool.isEmpty()){
                    try{
                        pool.wait();
                    }//end try
                    catch(InterruptedException ie){}
                }//end while
                connection = (Socket)pool.remove(0);
            }//end synchronized()        
        
            try{
                String filename = "";
                String contentType = "";
                OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
                Writer out = new OutputStreamWriter(raw);
                Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()), "ASCII");
                StringBuffer requestLine = new StringBuffer();
            
                /* read the user GET/POST data */
                int c;
                while(true){
                    c = in.read();
                    if(c == '\r' || c == '\n')
                        break;
                    requestLine.append((char)c);
                }//end while
            
                String get = requestLine.toString();
            
                /* log the request */
                System.out.println(get);
            
                StringTokenizer st = new StringTokenizer(get);
                String method = st.nextToken();
                String version = "";
                if(method.equals("GET")){
                    filename = st.nextToken();
                    if(filename.endsWith("/"))
                        filename += indexFileName;
                    contentType = guessContentTypeFromName(filename);
                    if(st.hasMoreTokens())
                        version = st.nextToken();
            
                    File theFile = new File(documentRootDirectory, filename.substring(1, filename.length()));
                    if(theFile.canRead() && theFile.getCanonicalPath().startsWith(root)){
                        DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(theFile)));
                        byte theData[] = new byte[(int)theFile.length()];
                        fis.readFully(theData);
                        fis.close();
                
                        /* Send MIME header */
                        if(version.startsWith("HTTP ")){
                            out.write("HTTP/1.0 200 OK\r\n");
                            Date now = new Date();
                            out.write("Data: " + now + "\r\n");
                            out.write("Server: JHTTP/1.0\r\n");
                            out.write("Content-type: " + contentType + "\r\n\r\n");
                            out.flush();
                        }//end if
                
                        /* Send the file, it may be an image or other binary data so use the underlying output stream instead of the writer */
                        raw.write(theData);
                        raw.flush();
                    
                    }//end if                
                    else{                    
                        if(version.startsWith("HTTP ")){
                            out.write("HTTP/1.0 404 FILE Not Found\r\n");
                            Date now = new Date();
                            out.write("Date: " + now + "\r\n");
                            out.write("Server: JHTTP/1,0\r\n");
                            out.write("Content-type: text/html\r\n\r\n");
                        }//end if
                        out.write("<HTML>\r\n");
                        out.write("<HEAD><TITLE>File Not Found</TITLE></HEAD>");
                        out.write("<BODY><H1>HTTP Error 404: File Not Found</H1>\r\n");
                        out.write("</BODY></HTML>\r\n");
                        out.flush();                    
                    }//end else
                }//end if
                else{
                    if(version.startsWith("HTTP ")){
                        out.write("HTTP/1.0 501 Not Implemented\r\n");
                        Date now = new Date();
                        out.write("Date: " + now + "\r\n");
                        out.write("Server: JHTTP/1,0\r\n");
                        out.write("Content-type: text/html\r\n\r\n");
                    }//end if
                    out.write("<HTML>\r\n");
                    out.write("<HEAD><TITLE>Not Implemented</TITLE></HEAD>");
                    out.write("<BODY><H1>HTTP Error 501: Not Implemented</H1>\r\n");
                    out.write("</BODY></HTML>\r\n");
                    out.flush();                    
                }//End else            
            }//End try
            catch(IOException ioe){}
            finally{
                try{
                    connection.close();
                }//end try
                catch(IOException ioe){}
            }//end finally            
        }//end while        
    }//End run() method
    
    /**
     * Get the response content based on file name
     */
    public static String guessContentTypeFromName(String name){
        
        if(name.endsWith(".html") || name.endsWith(".htm"))
            return "text/html";
        
        if(name.endsWith(".txt") || name.endsWith(".java"))
            return "text/plain";
        
        if(name.endsWith(".gif"))
            return "image/gif";
        
        if(name.endsWith(".class"))
            return "application/octet-stream";
        
        if(name.endsWith(".jpg") || name.endsWith(".jpeg"))
            return "image/jpeg";
        
        return "text/plain";
        
    }//end guessContentTypeFromName() method
    
}//end RequestProcessor class
