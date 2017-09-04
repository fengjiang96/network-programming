/*
 * UDPDiscardClient.java
 *
 * Created on 29 de Setembro de 2005, 21:57
 *
 */

import java.net.*;
import java.io.*;

/**
 * UDP discard client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPDiscardClient {
        
    public final static int DEFAULT_PORT = 9;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String hostname;
        int port = DEFAULT_PORT;
        
        /* check user command line parameters */
        if(args.length > 0){
            hostname = args[0];
            try{
                port = Integer.parseInt(args[1]);
            }//end try
            catch(Exception ex){}
        }//end if
        else{
            hostname = "localhost";
        }//end else
        
        try{
            InetAddress server = InetAddress.getByName(hostname);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket theSocket = new DatagramSocket();
            while(true){
                String theLine = userInput.readLine();
                byte data[] = theLine.getBytes();
                DatagramPacket theOutput = new DatagramPacket(data, data.length, server, port);
                theSocket.send(theOutput);
            }//end while
        }//end try
        catch(UnknownHostException uhe){
            System.err.println(uhe);
        }//end catch
        catch(SocketException se){
            System.err.println(se);
        }//end catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End UDPDiscardClient class