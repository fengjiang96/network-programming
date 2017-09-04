/*
 * EncoderTest.java
 *
 * Created on 11 de Setembro de 2005, 20:16
 *
 */

import java.io.*;
import java.net.*;

/**
 * Test several kinds of encoding
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class EncoderTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            System.out.println(URLEncoder.encode("This string has spaces", "UTF-8"));
            System.out.println(URLEncoder.encode("This*string*has*asterisks", "UTF-8"));
            System.out.println(URLEncoder.encode("This%string%has%percent%signs", "UTF-8"));
            
            System.out.println(URLEncoder.encode("This+string+has+pluses", "UTF-8"));
            System.out.println(URLEncoder.encode("This/string/has/slashes", "UTF-8"));
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks\"", "UTF-8"));
            
            System.out.println(URLEncoder.encode("This:string:has:colons", "UTF-8"));
            System.out.println(URLEncoder.encode("This~string~has~tildes", "UTF-8"));
            System.out.println(URLEncoder.encode("This(string)has(parentheses)", "UTF-8"));
            
            System.out.println(URLEncoder.encode("This.string.has.periods", "UTF-8"));
            System.out.println(URLEncoder.encode("This=string=has=equals", "UTF-8"));
            System.out.println(URLEncoder.encode("This&string&has&ampersands", "UTF-8"));
            System.out.println(URLEncoder.encode("Thiséstringéhasénon-ASCIIécharacters", "UTF-8"));            
        }//End try
        catch(UnsupportedEncodingException ex){
            throw new RuntimeException("Broken VM does not support UTF-8");
        }//End catch
        
    }//End main() method
    
}//End EncoderTest class