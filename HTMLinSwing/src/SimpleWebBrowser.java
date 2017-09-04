/*
 * SimpleWebBrowser.java
 *
 * Created on 13 de Setembro de 2005, 21:32
 *
 */

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * Implements a simple web browser to navigate on the web
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class SimpleWebBrowser {
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        /* get the first page */
        String initialPage = "http://www.cafeaulait.org/";
        if(args.length > 0)
            initialPage = args[0];
        
        /* set up the editor pane */
        JEditorPane jep = new JEditorPane();
        jep.setEditable(false);
        jep.addHyperlinkListener(new LinkFollower(jep));
        
        try{
            jep.setPage(initialPage);
        }//end try
        catch(IOException ioe){
            System.err.println("Usage: java SimpleWebBrowser url");
            System.err.println(ioe);
            System.exit(-1);
        }//End catch
        
        /* set up the window */
        JScrollPane scrollPane = new JScrollPane(jep);
        JFrame f = new JFrame("Simple Web Browser");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setContentPane(scrollPane);
        f.setSize(512,  342);
        EventQueue.invokeLater(new FrameShower(f));
        
    }//End main() method
    
    private static class FrameShower implements Runnable {
        
        private final Frame frame;
        
        /**
         * Creates a new FrameShower Object
         * @param frame A <code>Frame</code> representing the frame
         */
        public FrameShower(Frame frame){
            this.frame = frame;
        }//End FrameShower() constructor
        
        /**
         * @see java.lang.Thread#run()
         */
        public void run(){
            frame.setVisible(true);
        }//end run() method
        
    }//End FrameShower class
    
}//End SimpleWebBrowser class