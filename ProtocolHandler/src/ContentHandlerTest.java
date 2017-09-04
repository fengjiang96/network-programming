/*
 * ContentHandlerTest.java
 *
 * Created on 9 de Outubro de 2005, 23:12
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The tab-separated=values ContentTester class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ContentHandlerTest {
    
    private static void test(URL u) throws IOException {
        
        Object content = u.getContent();
        Vector v = (Vector)content;
        for(Enumeration e = v.elements(); e.hasMoreElements();){
            String sa[] = (String[])e.nextElement();
            for(int i = 0; i < sa.length; i++)
                System.out.print(sa[i] + "\t");
        }//end for
        System.out.println();
        
    }//End test() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int i = 0; i < args.length; i++){
            try{
                URL u = new URL(args[i]);
                test(u);
            }//end try
            catch(MalformedURLException mue){
                System.err.println(args[i] + " is not a good URL");
            }//end catch
            catch(Exception ex){
                ex.printStackTrace();
            }//End catch
        }//end for
        
    }//End main() method
    
}//end ContentHandlerTest class