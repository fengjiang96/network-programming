/*
 * RegistryLister.java
 *
 * Created on 12 de Outubro de 2005, 10:53
 *
 */

import java.rmi.*;
import java.net.*;

/**
 * Registry Lister
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class RegistryLister {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = 1099;
        
        if(args.length == 0){
            System.err.println("Usage: java RegistryLister host port");
            return;
        }//end if
        
        String host = args[0];
        if(args.length > 1){
            try{
                port = Integer.parseInt(args[1]);
                if(port < 1 || port > 65535)
                    port = 1099;
            }//end try
            catch(NumberFormatException nfe){}
        }//end if
        
        String url = "rmi://" + host + ":" + port + "/";
        try{
            String[] remoteObjects = Naming.list(url);
            for(int i = 0; i < remoteObjects.length; i++)
                System.out.println(remoteObjects[i]);
        }//end try
        catch(RemoteException re){
            System.err.println(re);
        }//end catch
        catch(MalformedURLException mue){
            System.err.println(mue);
        }//end catch
        
    }//End main() method
    
}//end RegistryServer class