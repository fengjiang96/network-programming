/*
 * FibonacciClient.java
 *
 * Created on 12 de Outubro de 2005, 09:35
 *
 */

import java.rmi.*;
import java.net.*;
import java.math.BigInteger;

/**
 * The Fibonacci Client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FibonacciClient {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length == 0 || !args[0].startsWith("rmi:")){
            System.err.println("Usage: java FibonacciClient rmi://host.domain:port/finbonacci number");
            return;
        }//end if
        
        try{
            Object o = Naming.lookup(args[0]);
            Fibonacci calculator = (Fibonacci)o;
            
            for(int i = 1; i < args.length; i++){
                try{
                    BigInteger index = new BigInteger(args[i]);
                    BigInteger f = calculator.getFibonacci(index);
                    System.out.println("The " + args[i] + "th Fibonacci number is " + f);
                }//end try
                catch(NumberFormatException nfe){
                    System.err.println(args[i] + " is not an integer");
                }//End catch                
            }//End for            
        }//End try
        catch(MalformedURLException mue){
            System.err.println(args[0] + " is not a valid RMI URL");            
        }//end catch
        catch(RemoteException re){
            System.err.println("Remote object threw exception " + re);
        }//End catch
        catch(NotBoundException nbe){
            System.err.println("Could not find the requested remote object on the server");
        }//End catch        
        
    }//End main() method
    
}//End FibonacciClient class