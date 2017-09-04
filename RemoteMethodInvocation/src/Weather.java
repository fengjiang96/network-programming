/*
 * Weather.java
 *
 * Created on 10 de Outubro de 2005, 22:01
 *
 */

import java.rmi.*;
import java.util.Date;

/**
 * The weather interface
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public interface Weather extends Remote {
    
    /**
     * Get the weather temperature
     * @return A <code>double</code> representing the weather temperature
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getTemperature() throws RemoteException;
    
    /**
     * Get the weather humidity
     * @return A <code>double</code> representing the weather humidity
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getHumidity() throws RemoteException;
    
    /**
     * Get the weather pressure
     * @return A <code>double</code> representing the weather pressure
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getPressure() throws RemoteException;
    
    /**
     * Get the weather wind speed
     * @return A <code>double</code> representing the weather wind speed
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getWindSpeed() throws RemoteException;
    
    /**
     * Get the weather wind direction
     * @return A <code>double</code> representing the weather wind direction
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getWindDirection() throws RemoteException;
    
    /**
     * Get the weather latitude
     * @return A <code>double</code> representing the weather latitude
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getLatitude() throws RemoteException;
    
    /**
     * Get the weather longitude
     * @return A <code>double</code> representing the weather longitude
     * @throws RemoteException if isn't possible to invoke the method
     */
    public double getLongitude() throws RemoteException;
    
    /**
     * Get the weather time
     * @return A <code>double</code> representing the weather time
     * @throws RemoteException if isn't possible to invoke the method
     */
    public Date getTime() throws RemoteException;
    
}//End Weather interface