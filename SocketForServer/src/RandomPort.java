/*
 * RandomPort.java
 *
 * Created on 19 de Setembro de 2005, 22:33
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.io.*;
import java.net.*;

/**
 * A random port
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class RandomPort {    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            ServerSocket server = new ServerSocket(0);
            System.out.println("This server runs on port " + server.getLocalPort());
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//End main() method
    
}//End RandomPort class