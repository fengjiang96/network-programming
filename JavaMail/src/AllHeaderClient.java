/*
 * AllHeaderClient.java
 *
 * Created on 16 de Outubro de 2005, 19:57
 *
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A program to read mail headers
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class AllHeaderClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        if(args.length == 0){
            System.err.println("Usage: java AttributeClient protocol://username@host/foldernane");
            return;
        }//end if
        
        URLName server = new URLName(args[0]);
        
        try{
            Session session = Session.getDefaultInstance(new Properties(), new MailAuthenticator(server.getUsername()));
            
            /* connect to the server and open the folder */
            Folder folder = session.getFolder(server);
            if(folder == null){
                System.out.println("Folder " + server.getFile() + " not found.");
                System.exit(1);
            }//end if
            folder.open(Folder.READ_ONLY);
            
            /* Get the message from the server */
            Message[] messages = folder.getMessages();
            for(int i = 0; i < messages.length; i++){
                System.out.println("------------ Messages " + (i + 1) + " ------------");
                
                Enumeration headers = messages[i].getAllHeaders();
                while(headers.hasMoreElements()){
                    Header h = (Header)headers.nextElement();
                    System.out.println(h.getName() + ": " + h.getValue());
                }//end while
                System.out.println();
            }//end for
            
            folder.close(false);
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
        }//End catch
        System.exit(0);
    }//End main() method
    
}//End AllHeaderClient class