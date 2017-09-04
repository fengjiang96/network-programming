/*
 * Assimilator.java
 *
 * Created on 13 de Outubro de 2005, 21:28
 *
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Sending a very simple mail message
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class Assimilator {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String users[] = {"fred", "giscard", "grandao", "leo", "riecken"};
        
        try{
            Properties props = new Properties();
            props.put("mail.host", "localhost");
            
            Session mailConnection = Session.getInstance(props, null);
            Message msg = new MimeMessage(mailConnection);
            
            for(int from = 0; from < users.length; from++){
                for(int to = 0; to < users.length; to++){
                    if(users[from].equals(users[to]))
                        continue;
                    
                    Address userFrom = new InternetAddress(users[to]);
                    Address userTo = new InternetAddress(users[from]);
            
                    msg.setContent("Mensagem de " + users[from] + " para " + users[to] + ".", "text/plain");
                    msg.setFrom(userFrom);
                    msg.setRecipient(Message.RecipientType.TO, userTo);
                    msg.setSubject("TESTE");
            
                    Transport.send(msg);
                }//end for
            }//end for
            
        }//end try
        catch(Exception ex){
            ex.printStackTrace();                    
        }//End catch
        
    }//End main() method
    
}//End Assimilator class