/*
 * SecurePOP3Client.java
 *
 * Created on 16 de Outubro de 2005, 11:07
 *
 */

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A POP client that ask the user for the password as necessary
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SecurePOP3Client {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Properties props = new Properties();
        String host = "localhost";
        String provider = "pop3";
        
        try{
            /* connect to the pop3 server */
            Session session = Session.getDefaultInstance(props, new MailAuthenticator());
            Store store = session.getStore(provider);
            store.connect(host, null, null);
            
            /* open the folder */
            Folder inbox = store.getFolder("INBOX");
            if(inbox == null){
                System.out.println("No INBOX");
                System.exit(1);
            }//end if
            inbox.open(Folder.READ_ONLY);
            
            /* Get the message from the server */
            Message[] messages = inbox.getMessages();
            for(int i = 0; i < messages.length; i++){
                System.out.println("---------- Message " + (i + 1) + " ----------");
                messages[i].writeTo(System.out);
            }//end for
            
            /* close the connection but don't remove the messages from the server */
            inbox.close(false);
            store.close();
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
        }//End catch
        
    }//end main() method
    
}//End SecurePOP3Client class