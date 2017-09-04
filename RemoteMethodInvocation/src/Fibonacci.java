/*
 * Fibonacci.java
 *
 * Created on 10 de Outubro de 2005, 22:32
 *
 */

import java.rmi.*;
import java.math.BigInteger;

/**
 * The Fibonacci interface
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public interface Fibonacci extends Remote {
    
    /**
     * Get the nth fibonacci number
     * @param n A <code>int</code> representing the nth number to catch
     * @return A <code>BigInteger</code> representing the fibonacci nth number
     * @throws RemoteException if isn't possible to invoke the method
     */
    public BigInteger getFibonacci(int n) throws RemoteException;
    
    /**
     * Get the nth fibonacci number
     * @param n A <code>BigInteger</code> representing the nth number to catch
     * @return A <code>BigInteger</code> representing the fibonacci nth number
     * @throws RemoteException if isn't possible to invoke the method
     */
    public BigInteger getFibonacci(BigInteger n) throws RemoteException;
    
}//End Fibonacci interface
