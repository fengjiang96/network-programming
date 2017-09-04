/*
 * UDPPoke.java
 *
 * Created on 3 de Outubro de 2005, 21:17
 *
 */

import java.io.*;
import java.net.*;

/**
 * The UDDPoke class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class UDPPoke {
    
    private int bufferSize;
    private DatagramSocket socket;
    private DatagramPacket outgoing;
    
    /**
     * Creates a new UDPPoke Object
     * @param host A <code>InetAddress</code> representing the ip address
     * @param port A <code>int</code> representing ghe port
     * @param bufferSize <code>int</code> representing the datagram size
     * @param timeout A <code>int</code> representing the package TTL timeout
     * @throws SocketException if isn't possible open the connection
     */
    public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) throws SocketException {
        
        outgoing = new DatagramPacket(new byte[1], 1, host, port);
        this.bufferSize = bufferSize;
        socket = new DatagramSocket(0);
        socket.connect(host, port);
        socket.setSoTimeout(timeout);
        
    }//End UDPPoke() constructor
    
    /**
     * Creates a new UDPPoke Object
     * @param host A <code>InetAddress</code> representing the ip address
     * @param port A <code>int</code> representing ghe port
     * @param bufferSize <code>int</code> representing the datagram size
     * @throws SocketException if isn't possible open the connection
     */
    public UDPPoke(InetAddress host, int port, int bufferSize) throws SocketException {
        this(host, port, bufferSize, 30000);
    }//End UDPPoke() constructor
    
    /**
     * Creates a new UDPPoke Object
     * @param host A <code>InetAddress</code> representing the ip address
     * @param port A <code>int</code> representing ghe port
     * @throws SocketException if isn't possible open the connection
     */
    public UDPPoke(InetAddress host, int port) throws SocketException {
        this(host, port, 8192, 30000);
    }//End UDPPoke() constructor
    
    /**
     * Send the message to the client and get the response
     * @param A <code>byte[]</code> representing the server response
     * @throws IOException if isn't possible send the message
     */
    public byte[] poke() throws IOException {
        
        byte[] response = null;
        try{
            socket.send(outgoing);
            DatagramPacket incoming = new DatagramPacket(new byte[bufferSize], bufferSize);
            socket.receive(incoming);
            
            int numBytes = incoming.getLength();
            response = new byte[numBytes];
            System.arraycopy(incoming.getData(), 0, response, 0, numBytes);            
        }//End try
        catch(IOException ioe){}
        
        return response;
        
    }//End poke() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        InetAddress host;
        int port = 0;
        
        try{
            host = InetAddress.getByName(args[0]);
            port = Integer.parseInt(args[1]);
            if(port < 1 || port > 65535) 
                throw new Exception();
        }//End try
        catch(Exception ex){
            System.err.println("Usage: java UDPPoke host port");
            return;
        }//End catch
        
        try{
            UDPPoke poker = new UDPPoke(host, port);
            byte[] response = poker.poke();
            if(response == null){
                System.out.println("No response within allotetd time");
                return;
            }//End if
            String result = "";
            try{
                result = new String(response, "ASCII");
            }//end try
            catch(UnsupportedEncodingException uee){
                result = new String(response, "8859_1");
            }//end catch
            System.out.println(result);
        }//end try
        catch(Exception ex){
            System.err.println(ex);
            ex.printStackTrace();
        }//End catch
        
    }//End main() method
    
}//End UDPPoke class