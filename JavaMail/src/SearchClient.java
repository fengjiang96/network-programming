/*
 * SearchClient.java
 *
 * Created on 16 de Outubro de 2005, 23:22
 *
 */

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.search.*;
import javax.mail.internet.*;

/**
 * A mail client that searches by from: address
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SearchClient {
    
    /**
     *
     */
    public static void processMultipart(Multipart mp){
        
    }//end processMultipart() method
    
    public static void processPart(Part p){
        
    }//End processPart() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.err.println("Usage: java SearchClient protocol://username@host/foldername");
            return;
        }//end if
        
        URLName server = new URLName(args[0]);
        
        try{
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
        }//end catch
        
    }//end main() method
    
}//End SearchClient class