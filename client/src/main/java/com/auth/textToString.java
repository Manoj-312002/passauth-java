package com.auth;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;


public class textToString {
    private byte [] xor1;
    private int width = 400 , height = 20;
    
    textToString( byte[] xor1 ){
        this.xor1 = xor1;
    }
    
    public void convertText( String text ) throws Exception{
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
    public void reconvert() throws Exception{
        
        BufferedImage img = ImageIO.read( new File("Text2.png")  );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData(); 

        for( int i = 0; i < bf.length; i++ ) bf[i] ^= xor1[ i%32 ];
        ImageIO.write(img , "png", new File("Text3.png"));

    }
}