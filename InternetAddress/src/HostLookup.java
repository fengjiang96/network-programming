/*
 * HostLookup.java
 *
 * Created on 11 de Setembro de 2005, 14:00
 * 
 */

import java.io.*;
import java.net.*;

/**
 * Get the hostname based on an IP address and vice-versa
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class HostLookup {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* User send command line parameter */
        if(args.length > 0){
            for(int i = 0; i < args.length; i++)
                System.out.println(lookup(args[i]));
        }//End if
        else{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter names and IP address. Enter \"exit\" to quit.");
            
            try{
                while(true){
                    String host = in.readLine();
                    if(host.equals("exit") || host.equalsIgnoreCase("quit"))
                        break;
                    System.out.println(lookup(host));
                }//End while
                
            }//End try
            catch(IOException ioex){
                System.err.println(ioex);
            }//End catch
        }//End else
        
    }//End main() method
    
    /*
     * Get the ip address based on hostname, or get the hostname based on ip address
     * @param host A <code>String</code> representing the hostname or ip address
     * @return A <code>String</code> representing the ip address or hostname
     */
    private static String lookup(String host){
        
        InetAddress node;
        
        /* get bytes from ip address */
        try{
            node = InetAddress.getByName(host);            
        }//End try
        catch(UnknownHostException uhex){
            return "Cannot find host " + host;
        }//End catch
        
        /* check if the host is a name or ip address */
        if(isHostname(host))
            return node.getHostAddress();
        else            
            return node.getHostName();        
        
    }//End lookup() method
    
    /*
     * Check if the host is a hostname
     * @param host A <code>String</code> representing the hostname or ip address
     * @return A <code>boolean</code> true if the host is a hostname
     */
    private static boolean isHostname(String host){
        
        /* if is an IPv6 address */
        if(host.indexOf(':') != -1)
            return false;
        
        char[] ca = host.toCharArray();
        
        /* if don't has a digit neither period */
        for(int i = 0; i < ca.length; i++)
            if(!Character.isDigit(ca[i]))
                if(ca[i] != '.')
                    return true;
        
        return false;
        
    }//End isHostname() method
    
}//End HostLookup class
