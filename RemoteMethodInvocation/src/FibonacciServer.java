/*
 * FibonacciServer.java
 *
 * Created on 10 de Outubro de 2005, 22:56
 *
 */

import java.net.*;
import java.rmi.*;

/**
 * The Fibonacci server class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FibonacciServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            FibonacciImpl f = new FibonacciImpl();
            Naming.rebind("fibonacci", f);
            System.out.println("Fibonacci Server ready.");
        }//end try
        catch(RemoteException re){
            System.out.println("Exception in FibonacciImpl.main: " + re);
        }//End catch
        catch(MalformedURLException mfu){
            System.out.println("MalformedURLException " + mfu);
        }//End catch
    }//End main() method
    
}//End FibonacciServer class