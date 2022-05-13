package com.auth;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;


public class textToImage {

    // changing widith - change width in byteToImage ( server ) and embedshuffle
    private int width = 398 , height = 24;
    
    public byte[] convertText( String text , byte[] hashed_password1 ) throws Exception{
        
        // create RGB image with width and height 
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g2d = img.createGraphics();                                                              // graphics background for img
        
        Font font = new Font("Arial", Font.PLAIN, 19);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();

        // * printing string
        System.out.println( "String width : " + fm.stringWidth(text) + " " + fm.getHeight() );
        System.out.println("\n");

        // rendering basics 
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // setting background colour
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2d.setColor(Color.BLACK);                                  // text colour

        g2d.drawString(text, 0, fm.getAscent() );
        g2d.dispose();

        
        // ? clear image printing
        ImageIO.write(img, "png", new File("Text1.png"));
        
        // getting buffer and xoring it
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for( int i = 0; i < bf.length; i++ ) bf[i] ^= hashed_password1[ i%32 ];
        System.out.println( bf.length );
        // ? saving for visualisation
        ImageIO.write(img, "png", new File("Text2.png"));

        return bf;
    }
    
    // trial xor to check if image is reconvertable
    public void reconvert( byte[] hashed_password1 ) throws Exception{
        
        BufferedImage img = ImageIO.read( new File("Text2.png")  );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData(); 

        for( int i = 0; i < bf.length; i++ ) bf[i] ^= hashed_password1[ i%32 ];
        
        // ? reconverted image printing
        ImageIO.write(img , "png", new File("Text3.png"));

    }
}