/*
 * SMTPClient.java
 *
 * Created on 15 de Outubro de 2005, 21:16
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.*;
import javax.mail.*;
import javax.swing.*;
import java.awt.event.*;
import javax.mail.internet.*;

/**
 * A graphical SMTP client
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SMTPClient extends JFrame {
    
    private JButton sendButton = new JButton("Send Message");       //send button
    private JLabel fromLabel = new JLabel("From: ");                //from label
    private JLabel toLabel = new JLabel("To: ");                    //to label
    private JLabel hostLabel = new JLabel("SMTP Server: ");         //host label
    private JLabel subjectLabel = new JLabel("Subject: ");          //subject label
    private JTextField fromField = new JTextField(40);              //from field
    private JTextField toField = new JTextField(40);                //to field
    private JTextField hostField = new JTextField(40);              //host field
    private JTextField subjectField = new JTextField(40);           //subject field
    private JTextArea message = new JTextArea(40, 72);              //message area
    private JScrollPane jsp = new JScrollPane(message);             //message area scroll pane
    
    /**
     * Creates a new SMTPClient object
     */
    public SMTPClient(){
        
        super("SMTP Client");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(4, 1));
        labels.add(hostLabel);
        labels.add(toLabel);
        labels.add(fromLabel);
        labels.add(subjectLabel);
        
        JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(4, 1));
        String host = System.getProperty("mail.host", "");
        hostField.setText(host);
        fields.add(hostField);        
        labels.add(toField);
        labels.add(fromField);
        labels.add(subjectField);
        
        Box north = Box.createHorizontalBox();
        north.add(labels);
        north.add(fields);
        contentPane.add(north, BorderLayout.NORTH);
        
        message.setFont(new Font("Monospaced", Font.PLAIN, 12));
        contentPane.add(jsp, BorderLayout.CENTER);
        
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(sendButton);
        sendButton.addActionListener(new SendAction());
        contentPane.add(south, BorderLayout.SOUTH);
        
        this.pack();
        
    }//End SMTPClient() constructor
    
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
                props.put("mail.host", hostField.getText());
                
                Session mailConnection = Session.getInstance(props, null);
                final Message msg = new MimeMessage(mailConnection);
                
                Address to = new InternetAddress(toField.getText());
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SMTPClient client = new SMTPClient();
        client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.show();
        
    }//End main() method
    
}//End SMTPClient class