import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/*
 * FibonacciImpl.java
 *
 * Created on 10 de Outubro de 2005, 22:45
 *
 */

/**
 * The FibonacciImpl class
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FibonacciImpl extends UnicastRemoteObject implements Fibonacci {
    
    /**
     * Creates a new FibonacciImpl Object
     */
    public FibonacciImpl() throws RemoteException {
        super();
    }//End FibonacciImpl() constructor

    /**
     * Get the nth fibonacci number
     * @param n A <code>int</code> representing the nth number to catch
     * @return A <code>BigInteger</code> representing the fibonacci nth number
     * @throws RemoteException if isn't possible to invoke the method
     */
    public BigInteger getFibonacci(int n) throws RemoteException {
        return this.getFibonacci(new BigInteger(Long.toString(n)));
    }//End getFibonacci() method

    /**
     * Get the nth fibonacci number
     * @param n A <code>BigInteger</code> representing the nth number to catch
     * @return A <code>BigInteger</code> representing the fibonacci nth number
     * @throws RemoteException if isn't possible to invoke the method
     */
    public BigInteger getFibonacci(BigInteger n) throws RemoteException {
        
        System.out.println("Calculating the " + n + "throws Fibonacci number");
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        
        if(n.equals(zero) || n.equals(one))
            return one;
        
        BigInteger i = one;
        BigInteger low = one;
        BigInteger high = one;
        
        while(i.compareTo(n) == -1){
            BigInteger temp = high;
            high = high.add(low);
            low = temp;
            i = i.add(one);
        }//end while
        
        return high;
        
    }//End getFibonacci() method
    
}//End FibonacciImpl class