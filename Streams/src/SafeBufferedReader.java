/*
 * SafeBufferedReader.java
 *
 * Created on August 7, 2005, 8:24 PM
 *
 */

import java.io.*;

/**
 * This class implements a Safetly BufferedReader to be used into Network Connection
 * @author Giscard Fernandes Faria
 */
public class SafeBufferedReader extends BufferedReader{
    
    private boolean lookingForLineFeed = false;
    
    /**
     * Creates a new SafeBufferedReader Object
     * @param in A <code>Reader</code> represent the input stream to buffer
     */
    public SafeBufferedReader(Reader in)
    {
        
        this(in, 1024);
        
    }//end SafeBufferedReader() constructor
    
    /**
     * Creates a new SafeBufferedReader Object
     * @param in A <code>Reader</code> represent the input stream to buffer
     * @param bufferSize An <code>int</code> representing the buffer size to use
     */
    public SafeBufferedReader(Reader in, int bufferSize){
        
        super(in, bufferSize);
        
    }//End SafBufferedReader() constructor
    
    public String readLine() throws IOException{
        
        StringBuffer sb = new StringBuffer("");
        
        while(true){
        
            int c = this.read();
            if(c == -1){
                if(sb.equals(""))
                    return null;
                return sb.toString();
            }//end if
            else if(c == '\n'){
                if(lookingForLineFeed){
                    lookingForLineFeed = false;
                    continue;
                }//end if
                else
                    return sb.toString();
            }//end if
            else if(c == '\r'){
                lookingForLineFeed = true;
                return sb.toString();
            }//end if
            else{
                lookingForLineFeed = false;
                sb.append((char)c);
            }//end else
                
            
        }//end while
        
    }//end readLine() method
           
    
}//End SafeBufferedReader class
