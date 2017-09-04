/*
 * Fibonacci.java
 *
 * Created on 13 de Setembro de 2005, 21:01
 *
 */

import java.io.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 * Display the fibonacci sequence using HTML format
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class Fibonacci {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StringBuffer result = new StringBuffer("<html><body><h1>Fibonacci Sequence<ol>");
        long f1 = 0;
        long f2 = 1;
        
        /* calculate the fibonacci sequence */
        for(int i = 0; i < 50; i++){
            result.append("<li>");
            result.append(f1);
            long temp = f2;
            f2 = f1 + f2;
            f1 = temp;
        }//end for
        
        result.append("</ol></body></html>");
        
        JEditorPane jep = new JEditorPane("text/html", result.toString());
        jep.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(jep);
        JFrame f = new JFrame("Fibonacci Sequence");
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setContentPane(scrollPane);
        f.setSize(512, 342);
        EventQueue.invokeLater(new FrameShower(f));
        
    }//end main() method
    
    private static class FrameShower implements Runnable {
        
        private final Frame frame;
        
        /**
         * Creates a new FrameShower object
         * @param frame A <code>Frame</code> representing the main frame
         */
        FrameShower(Frame frame){
            this.frame = frame;
        }//End FrameShower() constructor
        
        /**
         * @see java.lang.Thread#run()
         */
        public void run(){
            frame.setVisible(true);
        }//end run() method
        
    }//End FrameShower class
    
}//end Fibonacci class