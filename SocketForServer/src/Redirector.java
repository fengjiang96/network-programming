/*
 * Redirector.java
 *
 * Created on 20 de Setembro de 2005, 21:46
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * An HTTP Redirector
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class Redirector implements Runnable {
    
    private int port;                   //represent the port to use
    private String newSite;             //represent the new site to redirect
        
    /**
     * Creates a new Redirector Object
     * @param site A <code>String</code> representing the site to redirect
     * @param port A <code>int</code> representing the server port
     */
    public Redirector(String site, int port){        
        this.port = port;
        this.newSite = site;        
    }//End Redirector() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        try{
            ServerSocket server = new ServerSocket(this.port);
            System.out.println("Redirecting connections on port " + server.getLocalPort() + " to " + newSite);
            
            /* wait for the server */
            while(true){                
                try{
                    Socket s = server.accept();
                    Thread t = new RedirectThread(s);
                    t.start();
                }//end try
                catch(IOException ioe){}                
            }//End while            
        }//end try
        catch(BindException be){
            System.err.println("Could not start server. Port occupied.");
        }//end catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End run() method
    
    class RedirectThread extends Thread{
        
        private Socket connection;          //server connection
        
        /**
         * Creates a new RedirectThread Object
         * @param s A <code>Socket</code> representing the server connection
         */
        public RedirectThread(Socket s){
            this.connection = s;
        }//End RedirectThread() constructor
        
        /**
         * @see java.lang.Thread#run()
         */
        public void run(){
            
            try{
                Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "ASCII"));
                Reader in = new InputStreamReader(new BufferedInputStream(connection.getInputStream()));
                
                /* read the first line only, that's all we need */
                StringBuffer request = new StringBuffer(80);
                while(true){
                    int c = in.read();
                    if(c == '\r' || c == '\n' || c == -1)
                        break;
                    request.append((char)c);
                }//end while
                
                /* if this is HTTP/1.0 or later send a MIME header */
                String get = request.toString();
                int firstSpace = get.indexOf(' ');
                int secondSpace = get.indexOf(' ', firstSpace + 1);
                String theFile = get.substring(firstSpace + 1, secondSpace);
                
                /* get the HTTP protocol */
                if(get.indexOf("HTTP") != -1){
                    out.write("HTTP/1.0 302 FOUND\r\n");
                    Date now = new Date();
                    out.write("Date: " + now + "\r\n");
                    out.write("Server: Redirector 1.0\r\n");
                    out.write("Location: " + newSite + theFile + "\r\n");
                    out.write("Content-type: text/html\r\n\r\n");
                    out.flush();
                }//end if
                
                /* Not all browser support redirection so we need to produce html that says where the document has moved to */
                out.write("<HTML><HEAD><TITLE>Document Moved</TITLE></HEAD>\r\n");
                out.write("The document " + theFile + " has moved to\r\n<A HREF=\"" + newSite + theFile + "\"></A>.\r\n Please update your bookmarks<P>");
                out.write("</BODY></HTML>\r\n");
                out.flush();
                
            }//end try
            catch(IOException ioe){}
            finally{
                try{
                    if(connection != null)
                        connection.close();
                }//end try
                catch(IOException ioe){}
            }//end finally
            
        }//end run() method
        
    }//End RedirectThread class
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int thePort;
        String theSite;
        
        try{
            theSite = args[0];
            if(theSite.endsWith("/"))
                theSite = theSite.substring(0, theSite.length() -1);
        }//end try
        catch(Exception ex){
            System.out.println("Usage: java Redirector http://www.newsite.com/ port");
            return;
        }//end catch
        
        try{
            thePort = Integer.parseInt(args[1]);
        }//end try
        catch(Exception ex){
            thePort = 80;
        }//end catch
        
        Thread t = new Thread(new Redirector(theSite, thePort));
        t.start();
        
    }//End main() method
    
}//End Redirector class