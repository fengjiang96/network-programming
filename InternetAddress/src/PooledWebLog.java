/*
 * PooledWebLog.java
 *
 * Created on 11 de Setembro de 2005, 15:28
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.io.*;
import java.util.*;

/**
 *
 * @author User
 */
public class PooledWebLog {
    
    private BufferedReader in;
    private BufferedWriter out;
    private int numberOfThreads;
    private List entries = Collections.synchronizedList(new LinkedList());
    private boolean finished = false;
    private int test = 0;
        
    /** 
     * Creates a new PooledWebLog object
     * @param in A <code>InputStream</code> representing the input stream
     * @param out A <code>OutputStream</code> representing the output stream
     * @param numberOfThreads A <code>int</code> representing the threads quantity
     */
    public PooledWebLog(InputStream in, OutputStream out, int numberOfThreads){
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new BufferedWriter(new OutputStreamWriter(out));
        this.numberOfThreads = numberOfThreads;
    }//end PooledWebLog() constructor
    
    /**
     * Check if the log file is finished
     * @return A <code>boolean</code> representing if the log file was finished
     */
    public boolean isFinished(){
        return this.finished;
    }//End isFinished() method
    
    /**
     * Get all number of threads
     * @return A <code>int</code> representing the total number of threads
     */
    public int getNumberOfThreads(){
        return numberOfThreads;
    }//end getNumberOfThreads() method
    
    /**
     * Proccess all the log file
     * @throws IOException if isn't possible to log the file
     */
    public void proccessLogFile() throws IOException {
        
        for(int i = 0; i < numberOfThreads; i++){
            Thread t = new LookupThread(entries, this);
            t.start();
        }//end for
        
        String entry = in.readLine();
        while(entry != null){
            if(entries.size() > numberOfThreads){
                try{
                    Thread.sleep((long)1000.0 / numberOfThreads);
                }//end try                
                catch(InterruptedException iex){
                    continue;
                }//end catch
                    
                synchronized(entries){
                    entries.add(0, entry);
                    entries.notifyAll();
                }//end synchronized
                    
                entry = in.readLine();
                Thread.yield();                    
            }//end if
        }//end while
        
    }//End processLogFile() method
    
    /**
     * Write the entry into the log file
     * @param entry A <code>String</code> representing text to write
     * @throws IOException if isn't possible to write on file
     */
    public void log(String entry) throws IOException{
        out.write(entry + System.getProperty("line.separator", "\r\n"));
        out.flush();
    }//End log() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            PooledWebLog tw = new PooledWebLog(new FileInputStream(args[0]), System.out, 100);
            tw.proccessLogFile();
        }//End try
        catch(FileNotFoundException fnfex){
            System.err.println("Usage: java PooledWebLog logfile_name");
        }//End catch
        catch(ArrayIndexOutOfBoundsException ex){
            System.err.println("Usage: java PooledWebLog logfile_name");
        }//end catch
        catch(Exception ex){
            System.err.println(ex);
            ex.printStackTrace();
        }//end catch
        
    }//End main() method
    
}//End PooledWebLog class