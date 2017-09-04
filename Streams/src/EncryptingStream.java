/*
 * EncryptingStream.java
 *
 * Created on August 7, 2005, 6:53 PM
 *
 */

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * This class encrypt a simple txt file
 * @author Giscard Fernandes Faria
 */
public class EncryptingStream {    
    
    public static void main(String[] args) {
        
        String password = "teste123";                                           //user password        
        String path = "/mnt/slave/Network-Programming/Streams/build/classes/";  //basic file path
        String input = "resource/secrets.txt";                                  //raw file
        String output = "resource/secrets.des";                                 //encrypt file
        
        try{
            
            FileInputStream fin = new FileInputStream(path + input);
            FileOutputStream fout = new FileOutputStream(path + output);
            
            //register the algorithm provider
            Provider sunJCE = new com.sun.crypto.provider.SunJCE();
            Security.addProvider(sunJCE);
            
            //create a key
            char[] pbeKeyData = password.toCharArray();
            PBEKeySpec pbeKeySpec = new PBEKeySpec(pbeKeyData);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
            
            //use data encryption standard
            Cipher pbe = Cipher.getInstance("PBEWithMD5AndDES");
            pbe.init(Cipher.ENCRYPT_MODE, pbeKey);
            CipherOutputStream cout = new CipherOutputStream(fout, pbe);
            
            byte data[] = new byte[64];
            
            while(true){
                int bytesRead = fin.read(data);
                if(bytesRead == -1)
                    break;
                cout.write(data, 0, bytesRead);
            }//end while
            
        }//end try
        catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }//end catch
        
    }//end main() method
    
}//End EncryptingStream class
