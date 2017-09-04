/*
 * MailClient.java
 *
 * Created on 16 de Outubro de 2005, 17:26
 *
 */

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * A protocol-independent mail client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MailClient {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length == 0){
            System.err.println("Usage: java MailClient protocol://username:password@host/foldername");
            return;
        }//end if
        
        URLName server = new URLName(args[0]);
        
        try{
            Session session = Session.getDefaultInstance(new Properties(), null);
            
            /* Connect to the server and open the folder */
            Folder folder = session.getFolder(server);
            if(folder == null){
                System.out.println("Folder " + server.getFile() + " not found.");
                System.exit(1);
            }//end if
            folder.open(Folder.READ_ONLY);
            
            /* Get the mesages from the server */
            Message[] messages = folder.getMessages();
            for(int i = 0; i < messages.length; i++){
                System.out.println("---------- Message " + (i + 1) + " ----------");
                messages[i].writeTo(System.out);
            }//end for
            
            folder.close(false);
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();
        }//End catch
        
    }//end main() method
    
}//End MailClient class