/*
 * MulticastSniffer.java
 *
 * Created on 4 de Outubro de 2005, 22:40
 *
 */

import java.io.*;
import java.net.*;

/**
 * Multicast Sniffer
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MulticastSniffer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InetAddress group = null;
        int port = 0;
        
        /* read the address from the command line */
        try{
            group = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
        }//end try
        catch(Exception ex){
            System.err.println("Usage: java MulticasSniffer multicas_address port");
            System.exit(1);
        }//End catch
        
        MulticastSocket ms = null;
        
        try{
            ms = new MulticastSocket(port);
            ms.joinGroup(group);
            
            byte[] buffer = new byte[8192];
            while(true){
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ms.receive(dp);
                String s = new String(dp.getData());
                System.out.println(s);
            }//end while
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//End catch
        finally{
            if(ms != null){
                try{
                    ms.leaveGroup(group);
                    ms.close();
                }//end try
                catch(IOException ioe){}
            }//end if
        }//end finally
        
    }//End main() method
    
}//End MulticastSniffer class