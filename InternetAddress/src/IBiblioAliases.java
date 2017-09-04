/*
 * IBiblioAliases.java
 *
 * Created on 11 de Setembro de 2005, 12:06
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.net.*;

/**
 * Check if two InetAddress are the same machine
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class IBiblioAliases {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
            InetAddress helios = InetAddress.getByName("helios.metalab.unc.edu");
            
            if(ibiblio.equals(helios))
                System.out.println("www.ibiblio.org is the same host as helios.metalab.unc.edu");
            else
                System.out.println("www.ibiblio.org isn't the same host as helios.metalab.unc.edu");            
        }//end try
        catch(UnknownHostException uhex){
            System.out.println("Host lookup failed.");
        }//End catch
        
    }//End main() method
    
}//end IBiblioAliases class
