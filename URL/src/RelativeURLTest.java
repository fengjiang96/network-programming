/*
 * RelativeURLTest.java
 *
 * Created on 11 de Setembro de 2005, 17:53
 *
 */

import java.awt.*;
import java.net.*;
import java.applet.*;
import javax.swing.*;

/**
 * 
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class RelativeURLTest extends JApplet {
    
    /**
     * @see javax.swing.JApplet#init()
     */
    public void init(){        
        try{
            URL base = this.getDocumentBase();
            URL relative = new URL(base, "ProtocolTesterApplet.html");
            this.setLayout(new GridLayout(2, 1));
            this.add(new Label(base.toString()));
            this.add(new Label(relative.toString()));
        }//end try
        catch(MalformedURLException muex){
            this.add(new Label("This shouldn't happen!"));
        }//End catch        
    }//end init() method
    
}//End RelativeURLTest class