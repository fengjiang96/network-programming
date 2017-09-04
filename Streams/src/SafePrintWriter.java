/*
 * SafePrintWriter.java
 *
 * Created on August 7, 2005, 8:36 PM
 *
 */

import java.io.*;

/**
 * This class implements a Safetly PrintWrite to be used in Network Connection
 * @author Giscard Fernandes Faria
 */
public class SafePrintWriter extends Writer {
    
    protected Writer out;
    private boolean autoFlush = false;
    private String lineSeparator;
    private boolean closed = false;
    
    /**
     * Creates a new SafePrintWriter Object
     * @param out A <code>Writer</code> representing the output writer
     * @param lineSeparator A <code>String</code> representing the line separator string
     */
    public SafePrintWriter(Writer out, String lineSeparator)
    {
        
        this(out, false, lineSeparator);
        
    }//End SafePrintWriter() constructor
    
    /**
     * Creates a new SafePrintWriter Object
     * @param out A <code>Writer</code> representing the output writer
     * @param autoFlush A <code>boolean</code> representing whether the auto flush buffer is automatic or no
     * @param lineSeparator A <code>String</code> representing the line separator string
     * @throws NullPointerException when a line separator is NULL.
     */
    public SafePrintWriter(Writer out, boolean autoFlush, String lineSeparator)
    {
        
        super(out);
        this.out = out;
        this.autoFlush = autoFlush;
        if(lineSeparator == null)
            throw new NullPointerException("Null Line Separator");
        this.lineSeparator = lineSeparator;
        
    }//end SafePrintWriter() constructor
    
    /**
     * Creates a new SafePrintWriter Object
     * @param out A <code>OutputStream</code> representing the output writer
     * @param autoFlush A <code>boolean</code> representing whether the auto flush buffer is automatic or no
     * @param encoding A <code>String</code> representing the printer encode
     * @param lineSeparator A <code>String</code> representing the line separator string
     * @throws UnsupportedEncodingException when a invalid encode is used.
     */
    public SafePrintWriter(OutputStream out, boolean autoFlush, String encoding, String lineSeparator) throws UnsupportedEncodingException
    {
        
        this(new OutputStreamWriter(out, encoding), autoFlush, lineSeparator);
        
    }//end SafePrintWriter() constructor
    
    /**
     * Flush all data into the buffer
     * @throws IOException
     */
    public void flush() throws IOException
    {
        
        synchronized(lock){
            if(closed)
                throw new IOException("Stream closed");
            out.flush();
        }//end synchronized block
        
    }//end flush() method
    
    /**
     * Close the stream
     * @throws IOException when the stream was closed
     */
    public void close() throws IOException {
        
        try{
            this.flush();
        }//end try
        catch(IOException ioe){}
        
        synchronized(lock){
            out.close();
            this.closed = true;
        }//end synchronized block
        
    }//end close() method
    
    /**
     * Write data into the stream
     * @param c An <code>int</code> representing the data to write
     * @throws IOException when the stream was closed
     */
    public void write(int c) throws IOException
    {
        
        synchronized(lock){
            if(closed)
                throw new IOException("Stream closed");
            out.write(c);
        }//end synchronized block
        
    }//end write() method
    
    /**
     * Write data into the stream
     * @param text A <code>char[]</code> representing the data to write
     * @param offset An <code>int</code> representing the buffer offset
     * @param length An <code>int</code> representing the buffer length to write
     * @throws IOException when the stream was closed
     */
    public void write(char[] text, int offset, int length) throws IOException
    {
        
        synchronized(lock){
            if(closed)
                throw new IOException("Stream closed");
            out.write(text, offset, length);
        }//end synchronized block
        
    }//end write() method
    
    /**
     * Write data into the stream
     * @param text A <code>char[]</code> representing the data to write     
     * @throws IOException when the stream was closed
     */
    public void write(char[] text) throws IOException
    {
        
        synchronized(lock){
            if(closed)
                throw new IOException("Stream closed");
            out.write(text, 0, text.length);
        }//end synchronized block
        
    }//end write() method
    
    /**
     * Write data into the stream
     * @param s A <code>String</code> representing the data to write
     * @param offset An <code>int</code> representing the buffer offset
     * @param length An <code>int</code> representing the buffer length to write
     * @throws IOException when the stream was closed
     */
    public void write(String s, int offset, int length) throws IOException
    {
        
        synchronized(lock){
            if(closed)
                throw new IOException("Stream closed");
            out.write(s, offset, length);
        }//end synchronized block
        
    }//end write() method
   
    /**
     * Print data into the stream
     * @param b A <code>boolean</code> representing the data to write
     * @throws IOException
     */
    public void print(boolean b) throws IOException
    {
        
        if(b)
            this.write("true");
        else
            this.write("false");
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param b A <code>boolean</code> representing the data to write
     * @throws IOException
     */
    public void println(boolean b) throws IOException
    {
        
        if(b)
            this.write("true");
        else
            this.write("false");
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param c A <code>char</code> representing the data to write
     * @throws IOException
     */
    public void print(char c) throws IOException
    {
        
        this.write(String.valueOf(c));
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param c A <code>char</code> representing the data to write
     * @throws IOException
     */
    public void println(char c) throws IOException
    {
        
        this.write(String.valueOf(c));
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param i An <code>int</code> representing the data to write
     * @throws IOException
     */
    public void print(int i) throws IOException
    {
        
        this.write(String.valueOf(i));
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param i An <code>int</code> representing the data to write
     * @throws IOException
     */
    public void println(int i) throws IOException
    {
        
        this.write(String.valueOf(i));
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param l An <code>long</code> representing the data to write
     * @throws IOException
     */
    public void print(long l) throws IOException
    {
        
        this.write(String.valueOf(l));
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param l An <code>long</code> representing the data to write
     * @throws IOException
     */
    public void println(long l) throws IOException
    {
        
        this.write(String.valueOf(l));
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param f An <code>float</code> representing the data to write
     * @throws IOException
     */
    public void print(float f) throws IOException
    {
        
        this.write(String.valueOf(f));
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param f An <code>float</code> representing the data to write
     * @throws IOException
     */
    public void println(float f) throws IOException
    {
        
        this.write(String.valueOf(f));
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param d An <code>double</code> representing the data to write
     * @throws IOException
     */
    public void print(double d) throws IOException
    {
        
        this.write(String.valueOf(d));
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param d An <code>double</code> representing the data to write
     * @throws IOException
     */
    public void println(double d) throws IOException
    {
        
        this.write(String.valueOf(d));
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param text An <code>char[]</code> representing the data to write
     * @throws IOException
     */
    public void print(char text[]) throws IOException
    {
        
        this.write(text);
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param d An <code>double</code> representing the data to write
     * @throws IOException
     */
    public void println(char text[]) throws IOException
    {
        
        this.write(text);
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param s A <code>String</code> representing the data to write
     * @throws IOException
     */
    public void print(String s) throws IOException
    {
        
        if(s == null)
            this.write("null");
        else
            this.write(s);
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param s An <code>String</code> representing the data to write
     * @throws IOException
     */
    public void println(String s) throws IOException
    {
        
        if(s == null)
            this.write("null");
        else
            this.write(s);
        if(autoFlush)
            out.flush();
        
    }//end println() method
    
    /**
     * Print data into the stream
     * @param o A <code>Object</code> representing the data to write
     * @throws IOException
     */
    public void print(Object o) throws IOException
    {
        
        if(o == null)
            this.write("null");
        else
            this.write(o.toString());
        
    }//end print() method
    
    /**
     * Print data into the stream and print a line separator
     * @param o An <code>Object</code> representing the data to write
     * @throws IOException
     */
    public void println(Object o) throws IOException
    {
        
        if(o == null)
            this.write("null");
        else
            this.write(o.toString());
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
        
    }//end println() methodo
    
    /**
     * Print a line separator
     * @throws IOException
     */
    public void println() throws IOException {
        this.write(lineSeparator);
        if(autoFlush)
            out.flush();
    }//end println() method
    
    
}//End SafePrintWriter
