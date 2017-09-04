/*
 * SMTPApplet.java
 *
 * Created on 15 de Outubro de 2005, 22:27
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.awt.*;
import javax.mail.*;
import java.applet.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.internet.*;

/**
 * An applet that send email
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SMTPApplet extends Applet {
    
    private Button sendButton = new Button("Send Message");       //send button    
    private Label fromLabel = new Label("From: ");                //to label    
    private Label subjectLabel = new Label("Subject: ");          //subject label    
    private TextField fromField = new TextField(40);              //to field    
    private TextField subjectField = new TextField(40);           //subject field
    private TextArea message = new TextArea(40, 72);              //message area
    
    private String toAddress = "";
    
    /**
     * Creates a new SMTPApplet object
     */
    public SMTPApplet(){
        
        this.setLayout(new BorderLayout());
        
        Panel north = new Panel();
        north.setLayout(new GridLayout(3, 1));
        
        Panel n1 = new Panel();
        n1.add(fromLabel);
        n1.add(fromField);
        north.add(n1);
        
        Panel n2 = new Panel();
        n2.add(subjectLabel);
        n2.add(subjectField);
        north.add(n2);
        
        this.add(north, BorderLayout.NORTH);
        
        message.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.add(message, BorderLayout.CENTER);
        
        Panel south = new Panel();
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(sendButton);
        sendButton.addActionListener(new SendAction());
        this.add(south, BorderLayout.SOUTH);
        
    }//End SMTPApplet
    
    /**
     * @see java.awt.Applet#init()
     */
    public void init(){
        
        String subject = this.getParameter("subject");
        if(subject == null)
            subject = "";
        subjectField.setText(subject);
        
        toAddress = this.getParameter("to");
        if(toAddress == null)
            toAddress = "";
        
        String fromAddress = this.getParameter("from");
        if(fromAddress == null)
            fromAddress = "";
        fromField.setText(fromAddress);
        
    }//End init() method
    
    /*
     * This class implements the mail client action listener
     */
    class SendAction implements ActionListener{
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed()
         */
        public void actionPerformed(ActionEvent e){
            
            try{
                Properties props = new Properties();
                props.put("mail.host", getCodeBase().getHost());
                
                Session mailConnection = Session.getInstance(props, null);
                final Message msg = new MimeMessage(mailConnection);
                
                Address to = new InternetAddress(toAddress);
                Address from = new InternetAddress(fromField.getText());
                
                msg.setContent(message.getText(), "text/plain");
                msg.setFrom(from);
                msg.setRecipient(Message.RecipientType.TO, to);
                msg.setSubject(subjectField.getText());
                
                Runnable r = new Runnable(){
                    public void run(){
                        try{
                            Transport.send(msg);
                        }//end try
                        catch(Exception ex){
                            ex.printStackTrace();
                        }//end catch
                    }//end run() method
                };//end runnable
                Thread t = new Thread(r);
                t.start();
                message.setText("");
                
            }//end try
            catch(Exception ex){
                ex.printStackTrace();
            }//end catch
            
        }//End actionPerformed() method
        
    }//End SendAction class
    
}//End SMTPApplet class