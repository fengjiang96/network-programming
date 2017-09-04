/*
 * AttributeClient.java
 *
 * Created on 16 de Outubro de 2005, 19:34
 *
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A program to read mail attributes
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class AttributeClient {
        
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
                
                String from = InternetAddress.toString(messages[i].getFrom());
                if(from != null)
                    System.out.println("From: " + from);
                
                String to = InternetAddress.toString(messages[i].getRecipients(Message.RecipientType.TO));
                if(to != null)
                    System.out.println("To: " + to);
                
                String subject = messages[i].getSubject();                
                if(subject != null)
                    System.out.println("Subject: " + subject);
                
                Date sent = messages[i].getSentDate();
                if(sent != null)
                    System.out.println("Sent: " + sent);
                System.out.println();
                
                /* Here is the attributes */
                System.out.println("This message is approximately " + messages[i].getSize() + " bytes long.");
                System.out.println("This message has approximately " + messages[i].getLineCount() + " lines.");
                
                String disposition = messages[i].getDisposition();
                if(disposition == null);
                else if(disposition.equals(Part.INLINE))
                    System.out.println("This part should be displayed inline");
                else if(disposition.equals(Part.ATTACHMENT)){
                    System.out.println("This part is an attachment");                    
                    String fileName = messages[i].getFileName();
                    if(fileName != null)
                        System.out.println("The file name o this attachment is " + fileName);
                }//end if
                
                String description = messages[i].getDescription();
                if(description != null)
                    System.out.println("The description of this message is " + description);
                
            }//end for
            
            folder.close(false);
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
        }//End catch
        System.exit(0);
    }//End main() method
    
}//End AttributeClient class