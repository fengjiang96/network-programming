/*
 * ProtocolTester.java
 *
 * Created on 11 de Setembro de 2005, 16:32
 *
 */

import java.net.*;

/**
 * Test all supported protocol from the local VM
 * @author Giscard Fernandes Faria
 * @version 1.0
 */
public class ProtocolTester {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* hypertext transfer protocol */
        testProtocol("http://www.adc.org");
        
        /* secure http */
        testProtocol("https://www.amazon.com/exec/obidos/order2/");
        
        /* file transfer protocol */
        testProtocol("ftp://metalab.unc.edu/pub/languages/java/javafaq/");
        
        /* simple mail transfer protocol */
        testProtocol("mailto:elharo@metalab.unc.edu");
        
        /* telnet */
        testProtocol("telnet://dibner.poly.edu/");
        
        /* local file access */
        testProtocol("file://F:/J2ee-tutorial/SlideSample/build.xml");
        
        /* gopher */
        testProtocol("gopher://gopher.anc.org.za/");
        
        /* Lightweight Directory Access Protocol */
        testProtocol("ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");
        
        /* JAR */
        testProtocol("jar:http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!/com/macfaq/io/StreamCopier.class");
        
        /* NFS, Network File System */
        testProtocol("nfs://utopia.poly.edu/usr/tmp/");
        
        /* a custom protocol for JDBC */
        testProtocol("jdbc:mysql://luna.metalab.unc.edu:3306/NEWS");
        
        /* rmi, a custom protocol for remote method invocation */
        testProtocol("rmi://metalab.unc.edu/RenderEngine");
        
        /* custom protocols for HotJava */
        testProtocol("doc:/UsersGuide/release.html");
        testProtocol("netdoc:/UsersGuide/release.html");
        testProtocol("systemresource://www.adc.org/+/index.html");
        testProtocol("verbatim:http://www.adc.org/");
        
    }//End main() method
    
    /*
     * Try to create an object and check if the protocol is supported
     * @param url A <code>String</code> representing the complete urls
     */
    public static void testProtocol(String url){
        
        try{
            URL u = new URL(url);
            System.out.println(u.getProtocol() + " is supported");
        }//End try
        catch(MalformedURLException muex){
            String protocol = url.substring(0, url.indexOf(':'));
            System.out.println(protocol + " is not supported");
        }//End catch
        
    }//End testProtocol() method
    
}//End ProtocolTester class