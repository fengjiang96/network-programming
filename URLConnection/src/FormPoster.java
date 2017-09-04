/*
 * FormPoster.java
 *
 * Created on 6 de Outubro de 2005, 21:22
 *
 */

import java.io.*;
import java.net.*;

/**
 * Posting a form
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class FormPoster {
    
    private URL url;
    private QueryString query = new QueryString();    
    
    /**
     * Creates a new FormPoster object
     * @param url A <code>URL</code> representing the url to access
     */
    public FormPoster(URL url){
        if(!url.getProtocol().toLowerCase().startsWith("http"))
            throw new IllegalArgumentException("Posting only works for http URLs");
        this.url = url;
    }//End FormPoster() constructor
    
    /**
     * Add the set name and value to a query string
     */
    public void add(String name, String value){
        query.add(name, value);
    }//end add() method
    
    /**
     * Get the selected url
     * @return A <code>URL</code> representing the selected URL
     */
    public URL getURL(){
        return this.url;
    }//end getURL() method
    
    public InputStream post() throws IOException{
        
        /* open the connection and prepare it to POST */
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "ASCII");
        
        /*
         * The POST line, the Content-type header, and the Content-length headers are sent
         * by the URLConnection. We just need to send the data.
         */
        out.write(query.toString());
        out.write("\r\n");
        out.flush();
        out.close();
        
        return uc.getInputStream();
        
    }//End post() method
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        URL url;
        
        /* check the user parameter */
        if(args.length > 0){
            try{
                url = new URL(args[0]);
            }//end try
            catch(MalformedURLException ex){
                System.err.println("Usage: java FormPoster url");
                return;
            }//end catch
        }//end if
        else{
            try{
                url = new URL("http://www.cafeaulait.org/books/jnp3/postquery.phtml");
            }//end try
            catch(MalformedURLException ex){
                System.err.println(ex);
                return;
            }//end catch
        }//end else
        
        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliote Rusty Harold");
        poster.add("email", "elharo@metalab.unc.edu");
        
        try{
            InputStream in = poster.post();
            
            /* read the response */
            InputStreamReader r = new InputStreamReader(in);
            int c;
            while((c = r.read()) != -1)
                System.out.print((char)c);
            System.out.println();
            in.close();            
        }//end try
        catch(IOException ioe){
            System.err.println(ioe);
        }//end catch
        
    }//end main() method
    
}//End FormPoster class