/*
 * ClientTester.java
 *
 * Created on 19 de Setembro de 2005, 22:49
 *
 */

import java.io.*;
import java.net.*;

/**
 * A client tester
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ClientTester {    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* set the selected port */
        int port;
        try{
            port = Integer.parseInt(args[0]);
        }//end try
        catch(Exception ex){
            port = 0;
        }//end catch
        
        try{
            ServerSocket server = new ServerSocket(port, 1);
            System.out.println("Listening for connection on port " + server.getLocalPort());
            
            while(true){
                Socket connection = server.accept();
                try{
                    System.out.println("Connection established with " + connection);
                    Thread input = new InputThread(connection.getInputStream());
                    input.start();
                    Thread output = new OutputThread(connection.getOutputStream());
                    output.start();
                    
                    /* wait for output and input to finish */
                    try{
                        input.join();
                        output.join();
                    }//end try
                    catch(InterruptedException ie){}//End catch                    
                }//end try
                catch(IOException ioe){
                    System.err.println(ioe);
                }//end catch
                finally{
                    try{
                        if(connection != null)
                            connection.close();
                    }//end try
                    catch(IOException ioe){}//End catch
                }//end finally
            }//end while
        }//end try
        catch(IOException ioe){
            ioe.printStackTrace();
        }//end catch        
        
    }//end main() method
    
}//End ClientTester class

class InputThread extends Thread {
    
    InputStream in;
    
    /**
     * Creates a new InputThread object
     * @param in A <code>OutputStream</code> representing the input stream
     */
    public InputThread(InputStream in){
        this.in = in;
    }//end InputThread() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        try{
            while(true){
                int i = in.read();
                if(i == -1)
                    break;
                System.out.println(i);
            }//end while
        }//end try
        catch(SocketException se){}//End catch
        catch(IOException ioe){
            System.err.println();
        }//end catch
        try{
            in.close();
        }//end try
        catch(IOException ioe){}//End IOException
    }//end run() method
    
}//end InputThread class

class OutputThread extends Thread {
    
    private Writer out;
    
    /**
     * Creates a new OutputThread object
     * @param in A <code>OutputStream</code> representing the input stream
     */
    public OutputThread(OutputStream out){
        this.out = new OutputStreamWriter(out);
    }//end OutputThread() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        String line;
        BufferedReader in = new SafeBufferedReader(new InputStreamReader(System.in));
        try{
            while(true){
                line = in.readLine();
                if(line.equals("."))
                    break;
                out.write(line + "\r\n");
                out.flush();
            }//end while
        }//end try
        catch(IOException ioe){}//End catch
        try{
            out.close();
        }//end try
        catch(IOException ioe){}//end catch
    }//end run() method
    
}//end OutputThread class