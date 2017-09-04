/*
 * LookupThread.java
 *
 * Created on 11 de Setembro de 2005, 15:18
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This thread perform lookup operations
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LookupThread extends Thread {
    
    private List entries;       //tasks to do
    PooledWebLog log;           //used for callbacks
    
    public LookupThread(List entries, PooledWebLog log){
        
        this.entries = entries;
        this.log = log;
        
    }//end LookupThread() constructor
    
    /**
     * @see java.lang.Thread#run()
     */
    public void run(){
        
        String entry;
        while(true){
            synchronized(entries){
                while(entries.size() == 0){
                    if(log.isFinished())
                        return;
                    try{
                        entries.wait();
                    }//end try
                    catch(InterruptedException iex){}
                }//end while
                entry = (String)entries.remove(entries.size() - 1);
            }//end synchronized        
        
            int index = entry.indexOf(' ', 0);
            String remoteHost = entry.substring(0, index);
            String theRest = entry.substring(index, entry.length());
        
            try{
                remoteHost = InetAddress.getByName(remoteHost).getHostName();
            }//end try
            catch(Exception ex){}
        
            try{
                log.log(remoteHost + theRest);
            }//end try
            catch(IOException ioe){}
            
        }//End while
        
    }//End run() method
    
}//End LookupThread class