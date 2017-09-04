/*
 * LocalPortScanner.java
 *
 * Created on 15 de Setembro de 2005, 22:56
 *
 */

import java.io.*;
import java.net.*;

/**
 * Look for local ports
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LocalPortScanner {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int port = 1; port <= 65535; port++){            
            try{
                ServerSocket socket = new ServerSocket(port);
            }//end try
            catch(IOException ioe){
                System.out.println("There is a server on port " + port + ".");
            }//end catch            
        }//end for        
    }//End main() method
    
}//End LocalPortScanner class