/*
 * DialogAuthenticator.java
 *
 * Created on 12 de Setembro de 2005, 21:51
 *
 */

import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Represents a dialog authenticator to interface with password-protected sites
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class DialogAuthenticator extends Authenticator {
    
    private JDialog passwordDialog;
    private JLabel mainLabel = new JLabel("Please enter username and password: ");
    private JLabel userLabel = new JLabel("Username: ");
    private JLabel passwordLabel = new JLabel("Password: ");
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    
    /**
     * Creates a new DialogAuthenticator object
     */
    public DialogAuthenticator(){
        this("", new JFrame());
    }//End DialogAuthenticator() constructor
    
    /**
     * Creates a new DialogAuthenticator object
     * @param username A <code>String</code> representing the username
     */
    public DialogAuthenticator(String username){
        this(username, new JFrame());
    }//End DialogAuthenticator() constructor
    
    /**
     * Creates a new DialogAuthenticator object
     * @param parent A <code>JFrame</code> representing the parent frame
     */
    public DialogAuthenticator(JFrame parent){
        this("", parent);
    }//End DialogAuthenticator() constructor
    
    /**
     * Creates a new DialogAuthenticator objct
     * @param username A <code>String</code> representing the username
     * @param parent A <code>JFrame</code> representing the parent frame
     */
    public DialogAuthenticator(String username, JFrame parent){
        this.passwordDialog = new JDialog(parent, true);
        Container pane = passwordDialog.getContentPane();
        pane.setLayout(new GridLayout(4, 1));
        pane.add(mainLabel);
        JPanel p2 = new JPanel();
        p2.add(userLabel);
        p2.add(usernameField);
        usernameField.setText(username);
        pane.add(p2);
        JPanel p3 = new JPanel();
        p3.add(passwordLabel);
        p3.add(passwordField);
        pane.add(p3);
        JPanel p4 = new JPanel();
        p4.add(okButton);
        p4.add(cancelButton);
        pane.add(p4);
        passwordDialog.pack();
        
        ActionListener al = new OKResponse();
        okButton.addActionListener(al);
        usernameField.addActionListener(al);
        passwordField.addActionListener(al);
        cancelButton.addActionListener(new CancelResponse());
        
    }//End DialogAuthenticator() constructor
    
    private void show(){
        
        String prompt = this.getRequestingPrompt();
        if(prompt == null){
            String site = this.getRequestingSite().getHostName();
            String protocol = this.getRequestingProtocol();
            int port = this.getRequestingPort();
            if(site != null & protocol != null){
                prompt = protocol + "://" + site;
                if(port > 0)
                    prompt += ":" + port;
            }//end if
        }//end if
        else
            prompt = "";
        
        mainLabel.setText("Please enter username and password for " + prompt + ": ");
        passwordDialog.pack();
        passwordDialog.show();
        
    }//End show() method
    
    PasswordAuthentication response = null;
    
    class OKResponse implements ActionListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed()
         */
        public void actionPerformed(ActionEvent e){            
            passwordDialog.hide();
            char[] password = passwordField.getPassword();
            String username = usernameField.getText();
            passwordField.setText("");
            response = new PasswordAuthentication(username, password);            
        }//End actionPerformed() method
        
    }//End OKResponse class
    
    class CancelResponse implements ActionListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed()
         */
        public void actionPerformed(ActionEvent e){            
            passwordDialog.hide();
            passwordField.setText("");
            response = null;            
        }//End actionPerformed() method
        
    }//End CancelResponse class
    
    /**
     * Get the authenticate password
     */
    public PasswordAuthentication getPasswordAuthentication(){
        this.show();
        return this.response;
    }//End getPasswordAuthentication() method
        
}//end DialogAuthenticator class
