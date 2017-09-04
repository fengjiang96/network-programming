/*
 * SecureOrderTake.java
 *
 * Created on 21 de Setembro de 2005, 21:58
 *
 */

import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.*;
import javax.net.ssl.*;
import java.security.*;

/**
 * Secure Order Take
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SecureOrderTake {
    
    public final static int DEFAULT_PORT = 7000;            //default server port
    public final static String algorithm = "SSL";           //default cipher algorithm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int port = DEFAULT_PORT;
        
        /* check if the port is valid */
        if(args.length > 0){
            try{
                port = Integer.parseInt(args[0]);
                if(port < 0 || port >= 65536){
                    System.out.println("Port must between 0 and 65535");
                    return;
                }//end if
            }//end try
            catch(NumberFormatException ex){}
        }//end if
        
        try{
            SSLContext context = SSLContext.getInstance(algorithm);
            
            /* the reference implementation only supports X.509 keys */
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            
            /* Sun's default kind of key store */
            KeyStore ks = KeyStore.getInstance("JKS");
            
            /* For security, every key store is encrypted with a pass phrase that must be provide before we can load it from
             disk. The pass phrase is stored as a char[] array so it can be wiped from memory quickly rather than waiting for
             a garbage collector. Of course using a string literal here completely defeats that purpose */
            char password[] = "2andnotafnord".toCharArray();
            ks.load(new FileInputStream("jnp3e.keys"), password);
            kmf.init(ks, password);
            context.init(kmf.getKeyManagers(), null, null);
            
            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket)factory.createServerSocket(port);
            
            /* get all anonymous cipher suite */
            String supported[] = server.getSupportedCipherSuites();
            String anonCipherSuitesSupported[] = new String[supported.length];
            int numAnomCipherSuitesSupported = 0;
            for(int i = 0; i < supported.length; i++){
                if(supported[i].indexOf("_anon_") > 0)
                    anonCipherSuitesSupported[numAnomCipherSuitesSupported++] = supported[i];
            }//end for
            
            /* set all cipher suites */
            String oldEnabled[] = server.getEnabledCipherSuites();
            String newEnabled[] = new String[oldEnabled.length + numAnomCipherSuitesSupported];
            System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
            System.arraycopy(anonCipherSuitesSupported, 0, newEnabled, oldEnabled.length, numAnomCipherSuitesSupported);            
            server.setEnabledCipherSuites(newEnabled);
            
            /* Now all the set up is complete and we can focus on the actual communication */
            try{
                while(true){
                    /* This socket will be secure, but there's no indication of that in the code! */
                    Socket theConnection = server.accept();
                    InputStream in = theConnection.getInputStream();
                    
                    /* read the stream data */
                    int c;
                    while((c = in.read()) != -1)
                        System.out.write(c);
                    theConnection.close();                    
                }//end while
            }//end try
            catch(IOException ioe){
                System.err.println(ioe);
            }//End catch
        }//end try      
        catch(IOException ioe){
            ioe.printStackTrace();
        }//end catch
        catch(KeyManagementException km){
            km.printStackTrace();
        }//end catch
        catch(KeyStoreException ks){
            ks.printStackTrace();
        }//End catch
        catch(NoSuchAlgorithmException nsae){
            nsae.printStackTrace();
        }//end catch
        catch(java.security.cert.CertificateException ce){
            ce.printStackTrace();
        }//end catch
        catch(UnrecoverableKeyException uke){
            uke.printStackTrace();
        }//end catch
        
    }//End main() method
    
}//end SecureOrderTake class