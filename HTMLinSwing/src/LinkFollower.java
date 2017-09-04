/*
 * LinkFollower.java
 *
 * Created on 13 de Setembro de 2005, 21:27
 *
 */

import javax.swing.*;
import javax.swing.event.*;

/**
 * Creates a link follower event
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class LinkFollower implements HyperlinkListener {
    
    private JEditorPane pane;
    
    /**
     * Creates a new LinkFollower object
     * @param pane A <code>JEditorPane</code> representing the editor pane
     */
    public LinkFollower(JEditorPane pane){
        this.pane = pane;
    }//End LinkFollower() constructor
    
    /**
     * @see javax.swing.event.HyperlinkListener#hyperlinkUpdate()
     */
    public void hyperlinkUpdate(HyperlinkEvent event){
        
        if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED){
            try{
                pane.setPage(event.getURL());
            }//end try
            catch(Exception ex){
                pane.setText("<html>Could not load " + event.getURL() + "</html>");
            }//End catch
        }//end if
        
    }//End hyperlinkEvent() method
    
}//End LinkFollower class