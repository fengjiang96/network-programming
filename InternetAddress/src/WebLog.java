/*
 * WebLog.java
 *
 * Created on 11 de Setembro de 2005, 14:32
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Implments a Web Server log file parser
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class WebLog {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        Date start = new Date();                //start date
        try{
            FileInputStream fin = new FileInputStream(args[0]);
            Reader in = new InputStreamReader(fin);
            SafeBufferedReader bin = new SafeBufferedReader(in);
            
            String entry = null;
            while((entry = bin.readLine()) != null){
                
                /* separate out the ip address */                
                int index = entry.indexOf(' ', 0);
                String ip = entry.substring(0, index);
                String theRest = entry.substring(index, entry.length());
                
                /* find the hostname and print it out */
                try{
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);
                }//end try
                catch(UnknownHostException uhex){
                    System.out.println(entry);
                }//End catch
                
            }//end while
        }//End try
        catch(IOException ioex){
            System.out.println("Exception: " + ioex);
        }//end catch
        
        Date end = new Date();
        long elapsedTime = (end.getTime() - start.getTime()) / 1000;
        System.out.println("Elapsed Time: " + elapsedTime + " seconds");
        
    }//End main() method
    
}//End WebLog class