/*
 * DatagramExample.java
 *
 * Created on 28 de Setembro de 2005, 22:25
 *
 */

import java.net.*;

/**
 * Construct a DatagramPackets to receive data
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DatagramExample {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        String s = "This is a test.";
        
        byte[] data = s.getBytes();
        try{
            InetAddress ia = InetAddress.getByName("www.ibiblio.org");
            
            int port = 7;
            DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);            
            System.out.println("This packet is address to " + dp.getAddress() + "on port " + port);
            System.out.println("There are " + dp.getLength() + " bytes of data in the packet");
            System.out.println(new String(dp.getData(), dp.getOffset(), dp.getLength()));            
        }//end try
        catch(UnknownHostException uhe){
            System.err.println(uhe);
        }//End catch
        
    }//end main() method
    
}//End DatagramExample class