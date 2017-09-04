/*
 * MailAuthenticator.java
 *
 * Created on 16 de Outubro de 2005, 10:52
 *
 */

import java.awt.*;
import javax.mail.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * GUI Authenticator
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class MailAuthenticator extends Authenticator {
    
    private JDialog passwordDialog = new JDialog(new JFrame(), true);
    private JLabel mainLabel = new JLabel("Please enter your user name and password");
    private JLabel userLabel = new JLabel("User Name: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton okButton = new JButton("OK");
    
    /**
     * Creates a new MailAuthenticator object
     */
    public MailAuthenticator(){
        this("");
    }//End MailAuthenticator() constructor
    
    /**
     * Creates a new MailAuthenticator object
     * @param username A <code>String</code> representing the username
     */
    public MailAuthenticator(String username){
        
        Container pane = passwordDialog.getContentPane();
        pane.setLayout(new GridLayout(4, 1));
        pane.add(mainLabel);
        
        JPanel p2 = new JPanel();
        p2.add(userLabel);
        p2.add(usernameField);
        pane.add(p2);
        
        usernameField.setText(username);
        
        JPanel p3 = new JPanel();
        p3.add(passwordLabel);
        p3.add(passwordField);
        pane.add(p3);
        
        JPanel p4 = new JPanel();
        p4.add(okButton);
        pane.add(p4);
        
        passwordDialog.pack();
        
        ActionListener al = new HideDialog();
        okButton.addActionListener(al);
        usernameField.addActionListener(al);
        passwordField.addActionListener(al);
        
    }//End MailAuthenticator() constructor
    
    /**
     * @see javax.mail.Authenticator#getPasswordAuthentication
     */
    public PasswordAuthentication getPasswordAuthentication(){
        
        passwordDialog.show();
        
        /*
         * Get the password returns an array of chars for security reasons. We need
         * to convert it to a String for the PasswordAutnetication() constructor.
         */
        String password = new String(passwordField.getPassword());
        String username = usernameField.getText();
        
        /*
         * Erase the password in case this is used again. The provider should
         * cache the password if necessary
         */
        passwordField.setText("");
        return new PasswordAuthentication(username, password);
        
    }//End getPasswordAuthentication() method
    
    class HideDialog implements ActionListener{
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed()
         */
        public void actionPerformed(ActionEvent e){
            passwordDialog.hide();
        }//end actionPerformed() method
        
    }//end HideDialog class
    
}//End MailAuthenticator class