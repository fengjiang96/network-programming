/*
 * HeaderClient.java
 *
 * Created on 16 de Outubro de 2005, 18:20
 *
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A program to read the mail headers
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class HeaderClient {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.err.println("Usage: java HeaderClient protocol://username@host/foldername");
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
            
            /* get the message from the server */
            Message[] messages = folder.getMessages();
            for(int i = 0; i < messages.length; i++){
                System.out.println("------------ Mesages " + (i + 1) + " ------------");
                
                /* here is the big change... */
                String from = InternetAddress.toString(messages[i].getFrom());
                if(from != null)
                    System.out.println("FROM: " + from);
                
                String replyTo = InternetAddress.toString(messages[i].getReplyTo());
                if(replyTo != null)
                    System.out.println("REPLY TO: " + replyTo);
                
                String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
                if(to != null)
                    System.out.println("TO: " + to);
                
                String cc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.CC));
                if(cc != null)
                    System.out.println("CC: " + cc);
                
                String bcc = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.BCC));
                if(bcc != null)
                    System.out.println("BCC: " + bcc);
                
                String subject = messages[i].getSubject();
                if(subject != null)
                    System.out.println("SUBJECT: " + subject);
                
                Date sent = messages[i].getSentDate();
                if(sent != null)
                    System.out.println("SENT: " + sent);
                
                Date received = messages[i].getReceivedDate();
                if(received != null)
                    System.out.println("RECEIVED: " + received);
                
                System.out.println();
            }//end for
            
            folder.close(false);
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();                    
        }//end catch
        System.exit(0);
        
    }//end main() method
    
}//End HeaderClient class