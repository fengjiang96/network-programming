/*
 * QueryString.java
 *
 * Created on 11 de Setembro de 2005, 20:28
 *
 */

import java.io.*;
import java.net.*;

/**
 * Implements a QueryString encoder
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class QueryString {
    
    private StringBuffer query = new StringBuffer();
    
    public QueryString(){
        
    }//End QueryString() constructor
    
    /**
     * Creates a new QueryString Object
     * @param name A <code>String</code> representing the query string
     * @param value A <code>String</code> representing the query value
     */
    public QueryString(String name, String value){
        encode(name, value);
    }//End QueryString() constructor
    
    /**
     * Add a new name and value to the query
     * @param name A <code>String</code> representing the var name
     * @param value A <code>String</code> representing the var value
     */
    public synchronized void add(String name, String value){
        query.append('&');
        encode(name, value);
    }//End add() method
    
    /**
     * Encode the query
     * @param name A <code>String</code> representing the query name
     * @param value A <code>String</code> representing the query value
     */
    public void encode(String name, String value){
        try{
            query.append(URLEncoder.encode(name, "UTF-8"));
            query.append('=');
            query.append(URLEncoder.encode(value, "UTF-8"));
        }//end try
        catch(UnsupportedEncodingException uee){
            throw new RuntimeException("Broken VM does not support UTF-8");
        }//end catch
    }//End encode() method
    
    /**
     * Get the mounted query
     * @return A <code>String</code> representing the query
     */
    public String getQuery(){
        return query.toString();
    }//end getQuery() method
    
    /**
     * @see java.lang.String#toString()
     */
    public String toString(){
        return getQuery();
    }//End toString() method
    
}//End QueryString class
