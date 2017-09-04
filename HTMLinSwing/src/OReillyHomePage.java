/*
 * OReillyHomePage.java
 *
 * Created on 13 de Setembro de 2005, 20:55
 *
 */

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * Create an editor to display HTML pages
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class OReillyHomePage {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);
        
        try{
            jep.setPage("http://www.oreilly.com");            
        }//end try
        catch(IOException ioe){
            jep.setContentType("text/html");
            jep.setText("<html>Could not load http://www.oreilly.com</html>");
        }//end catch
        
        JScrollPane scrollPane = new JScrollPane(jep);
        JFrame f = new JFrame("O'Reilly & Associates");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setContentPane(scrollPane);
        f.setSize(512, 342);
        f.show();
        
    }//end main() method
    
}//end OReillyHomePage class