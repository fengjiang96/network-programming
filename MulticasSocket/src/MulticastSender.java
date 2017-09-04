/*
 * MulticastSender.java
 *
 * Created on 4 de Outubro de 2005, 23:04
 *
 */

import java.io.*;
import java.net.*;

/**
 * Multicast Sender
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MulticastSender {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InetAddress ia = null;
        int port = 0;
        byte ttl = (byte)1;
        
        /* read the address command line */
        try{
            ia = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
            if(args.length > 2)
                ttl = (byte)Integer.parseInt(args[2]);
        }//end try
        catch(Exception ex){
            System.err.println(ex);
            System.err.println("Usage: java MulticastSender multicast_address port ttl");
            System.exit(1);
        }//end catch
        
        byte[] data = "Here's some multicast data\r\n".getBytes();
        DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
        
        try{            
            MulticastSocket ms = new MulticastSocket();
            ms.joinGroup(ia);
            for(int i = 1; i < 10; i++)
                ms.send(dp, ttl);                
            ms.leaveGroup(ia);
            ms.close();
        }//end try
        catch(SocketException se){
            System.err.println(se);
        }//end catch
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End MulticasSenderc class