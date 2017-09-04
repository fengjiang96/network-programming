/*
 * FibonacciApplet.java
 *
 * Created on 12 de Outubro de 2005, 10:18
 *
 */

import java.rmi.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.math.BigInteger;

/**
 * An applet client for the fibonacci object
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FibonacciApplet extends Applet {
    
    private TextArea resultArea = new TextArea("", 20, 72, TextArea.SCROLLBARS_BOTH);
    private TextField inputArea = new TextField(24);
    private Button calculate = new Button("Calculate");
    private String server;
    
    /**
     * @see java.applet.Applet#init()
     */
    public void init(){
        
        this.setLayout(new BorderLayout());
        Panel north = new Panel(new FlowLayout());
        north.add(new Label("Type a non-negative integer"));
        north.add(inputArea);
        north.add(calculate);
        this.add(resultArea, BorderLayout.CENTER);
        this.add(north, BorderLayout.NORTH);
        Calculator c = new Calculator();
        inputArea.addActionListener(c);
        calculate.addActionListener(c);
        resultArea.setEditable(false);
        
        server = "rmi:// " + this.getCodeBase().getHost() + "/fibonacci";
        
    }//End init() method
    
    class Calculator implements ActionListener {
        
        public void actionPerformed(ActionEvent event){
            
            try{
                String input = inputArea.getText();
                if(input != null){
                    BigInteger index = new BigInteger(input);
                    Fibonacci f = (Fibonacci)Naming.lookup(server);
                    BigInteger result = f.getFibonacci(index);
                    resultArea.setText(result.toString());
                }//end if
            }//end try
            catch(Exception ex){
                resultArea.setText(ex.getMessage());
            }//End catch
            
        }//End actionPerformed() method
        
    }//End Calculator class
    
}//End FibonacciApplet class