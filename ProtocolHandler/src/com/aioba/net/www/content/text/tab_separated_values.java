/*
 * tab_separated_values.java
 *
 * Created on 9 de Outubro de 2005, 22:49
 *
 */

package com.aioba.net.www.content.text;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * A Content handler for text/tab-separated-values
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class tab_separated_values extends ContentHandler {
    
    public Object getContent(URLConnection uc) throws IOException {
        
        String theLine;
        Vector lines = new Vector();
        
        InputStreamReader isr = new InputStreamReader(uc.getInputStream());
        SafeBufferedReader in = new SafeBufferedReader(isr);        
        //SafeBufferedReader in = new SafeBufferedReader(isr);
        while((theLine = in.readLine()) != null){
            String[] linearray = lineToArray(theLine);
            lines.addElement(linearray);
        }//End while
        
        return lines;
        
    }//End getContent() method
    
    /*
     * Broke a tabbed line to an array of string
     * @param line A <code>String</code> representing the entire line
     * @return A <code>String[]</code> representing the array of string
     */
    private String[] lineToArray(String line){
        
        int numFields = 1;
        for(int i = 0; i < line.length(); i++)
            if(line.charAt(i) == '\t')
                numFields++;
        
        String[] fields = new String[numFields];
        int position = 0;
        for(int i = 0; i < numFields; i++){
            StringBuffer buffer = new StringBuffer();
            while(position < line.length() && line.charAt(position) != '\t'){
                buffer.append(line.charAt(position));
                position++;
            }//end while
            fields[i] = buffer.toString();
            position++;
        }//end for
        return fields;
        
    }//End lineToArray() method
    
}//end tab_separated_values class