/*
 * SingleFileHTTPServer.java
 *
 * Created on 20 de Setembro de 2005, 21:09
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * An HTTP server that chunks out the same file
 * @author Giscard Feranandes Faria
 * @version 1.0
 */
public class SingleFileHTTPServer extends Thread {
    
    private byte[] content;             //represent the response content
    private byte[] header;              //represent the response header
    private int port = 80;              //represent the server port
    
    /**
     * Creates a new SingleFileHTTPServer Object
     * @param data A <code>String</code> representing the data format
     * @param encoding A <code>String</code> representing the data encoding
     * @param MIMEType A <code>String</code> representing the MIME data type
     * @param port A <code>int</code> representing the port
     */
    public SingleFileHTTPServer(String data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException{
        this(data.getBytes(encoding), encoding, MIMEType, port);
    }//End SingleFileHTTPServer() constructor
    
    /**
     * Creates a new SingleFileHTTPServer Object
     * @param data A <code>byte[]</code> representing the data format
     * @param encoding A <code>String</code> representing the data encoding
     * @param MIMEType A <code>String</code> representing the MIME data type
     * @param port A <code>int</code> representing the port
     */
    public SingleFileHTTPServer(byte[] data, String encoding, String MIMEType, int port) throws UnsupportedEncodingException{
        this.content = data;
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" +
                        "Server: OneFile 1.0\r\n" +
                        "Content-length: " + this.content.length + "\r\n" +
                        "Content-type: " + MIMEType + "\r\n\r\n";
        this.header = header.getBytes("ASCII");
    }//End SingleFileHTTPServer() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        try{
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Accepting connection on port " + server.getLocalPort());
            System.out.println("Data to be sent: ");
            System.out.write(this.content);
            
            /* while expect user connection */
            while(true){                
                Socket connection = null;
                try{
                    connection = server.accept();
                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    
                    /* read the first line only; that's all we need */
                    StringBuffer request = new StringBuffer(80);
                    while(true){
                        int c = in.read();
                        if(c == '\r' || c == '\n' || c == -1)
                            break;
                        request.append((char)c);
                    }//end while
                    
                    /* if this is HTTP/1.0 or later send a MIME header */
                    if(request.toString().indexOf("HTTP/") != -1)
                        out.write(this.header);                    
                    out.write(this.content);
                    out.flush();                    
                }//end try
                catch(IOException ioe){}
                finally{
                    if(connection != null)
                        connection.close();
                }//end finally                
            }//end while
        }//end try
        catch(IOException ioe){
            System.err.println("Could not start server. Port Occupied");
        }//end catch
        
    }//End run() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            String contentType = "text/plain";
            if(args[0].endsWith(".html") || args[0].endsWith(".htm"))
                contentType = "text/html";
            
            InputStream in = new FileInputStream(args[0]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            
            /* read the entire file */
            int b;
            while((b = in.read()) != -1)
                out.write(b);
            byte[] data = out.toByteArray();
            
            /* see the port to listen on */
            int port;
            try{                
                port = Integer.parseInt(args[1]);
                if(port < 1 || port > 65535)
                    port = 80;                
            }//end try
            catch(Exception ex){
                port = 80;
            }//end catch
            
            /* set the page encoding */
            String encoding = "ASCII";
            if(args.length >= 2)
                encoding = args[2];
            
            Thread t = new SingleFileHTTPServer(data, encoding, contentType, port);
            t.start();
            
        }//end try
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Usage:  + java SingleFileHTTPServer filename port encoding");
        }//End catch
        catch(Exception ex){
            System.err.println(ex);
        }//end catch
        
    }//End main() method
    
}//End SingleFileHTTPServer class
