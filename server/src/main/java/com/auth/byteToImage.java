package com.auth;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class byteToImage {

    private int width = 243 , height = 15;
    // xors xor1_img and hashed_password1 to return ctext_byte , and stores the ctext as image
    public byte[] convertToImage( byte[] xor1_img ,  byte[] hashed_password1 ) throws Exception{
        Integer xor1_img_len =  xor1_img.length;
        byte[] ctext_byte = xor1_img;

        for( int i = 0; i < xor1_img_len; i++  ){
            ctext_byte[i] ^= hashed_password1[ i%32 ];
        }

        // create RGB image with width and height 
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g2d = img.createGraphics();                                                              // graphics background for img


        // rendering basics 
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);

        // getting buffer and xoring it
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for( int i = 0; i < bf.length; i++ ) bf[i] = ctext_byte[ i ];
        
        
        ImageIO.write(img, "png", new File("Text7.png"));

        return ctext_byte;
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