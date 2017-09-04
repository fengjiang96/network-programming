/*
 * HTMLLabelApplet.java
 *
 * Created on 12 de Setembro de 2005, 23:05
 *
 */

import javax.swing.*;

/**
 * Create a swing label using HTML tags
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class HTMLLabelApplet extends JApplet {
    
    /**
     * javax.swing.JApplet#init()
     */
    public void init(){
        
        JLabel theText = new JLabel("<html>Hello! This is a multiple label with <b>bold</b> and <i>" + 
                "italic</i> text. <P> It can use paragraphs, horizontal lines, <hr><font color=red>colors" + 
                "</font> and most of the other basic features of HTML 3.2</html>");
        this.getContentPane().add(theText);
        
    }//end init() method
    
}//End HTMLLabelApplet class