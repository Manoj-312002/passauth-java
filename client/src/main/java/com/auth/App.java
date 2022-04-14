package com.auth;
import java.security.*;
import java.util.*; 
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class App {
    private static byte [] xor1;
    private static int width = 400 , height = 20;
    
    public static void convertText( String text ) throws Exception{
        System.out.println(text);
        System.out.println(width + " " + height);
        
        // create RGB image with width and height 
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g2d = img.createGraphics();                                                              // graphics background for img
        
        Font font = new Font("Arial", Font.PLAIN, 20);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setFont(font);

        // rendering basics
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // setting background colour
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2d.setColor(Color.BLACK);                                  // text colour

        g2d.drawBytes(xor1, 0, xor1.length, 10, 30);

        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        

        /*
            // byte[] pixels2 = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
            // StringBuilder sb1 = new StringBuilder(); for (byte b : pixels2 )  sb1.append(String.format("%02x", b));
            // System.out.println(sb1.toString());
            // int ct = 0;
            // for( int i = 0; i < width; i++ ){
            //     for( int j = 0; j < height; j++ ){
            //         img.setRGB(x, y, rgb);
            //     }
            // }
        */
        
        // clear image printing
        ImageIO.write(img, "png", new File("Text1.png"));
        
        // getting buffer and xoring it
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        
        System.out.println( bf.length );
        for( int i = 0; i < bf.length; i++ ) bf[i] ^= xor1[ i%32 ];
        
        // saving for visualisation
        ImageIO.write(img, "png", new File("Text2.png"));

    }
    
    // trial xor to check if image is reconvertable
    public static void reconvert() throws Exception{
        
        BufferedImage img = ImageIO.read( new File("Text2.png")  );

        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData(); 

        for( int i = 0; i < bf.length; i++ ) bf[i] ^= xor1[ i%32 ];
        ImageIO.write(img , "png", new File("Text3.png"));
    }

    public static void main( String[] args ) throws Exception {
        
        /*
            s - seed
            Pc - password client 
            CText - clear text sent from server
            xor1 - xor of CText and h( pc , s )
        */
        
        Random rd = new Random(); Integer s = rd.nextInt();
        String Pc = "4443354h";
        byte [] CText = UUID.randomUUID().toString().replaceAll("-", "" ).getBytes();

        // hashing password
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        md.update( s.byteValue() );
        byte[] hashed_password = md.digest( Pc.getBytes("utf-8") );
        

        // printing hashed password
        StringBuilder sb = new StringBuilder(); for (byte b : hashed_password )  sb.append(String.format("%02x", b));
        System.out.println(sb.toString());
        
        // xoring CText and h( pc , s )
        xor1 = CText;
        for( int i = 0; i < 32; i++ ) xor1[i] ^= hashed_password[i];
        
        // representing xor1 in hex
        StringBuilder sb1 = new StringBuilder(); for (byte b : xor1 )  sb1.append(String.format("%02x", b));
        System.out.println(sb1.toString());

        
        String xor1S = new String( xor1 , "ascii" );
        System.out.println( Arrays.toString( xor1 ) );
        System.out.println( xor1S );
        
        convertText( xor1S );
        reconvert();

        // textToImg tx = new textToImg();
    }
}
