package com.auth;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class byteToImage {

    private int width = 1300 , height = 29;

    // xors xor1_img and hashed_password1 to return ctext_byte , and stores the ctext as image
    public byte[] convertToImage( byte[] xor1_img ,  byte[] hashed_password1 ) throws Exception{
        Integer xor1_img_len =  xor1_img.length;
        byte[] ctext_byte = xor1_img;

        for( int i = 0; i < xor1_img_len; i++  ){   
            ctext_byte[i] ^= hashed_password1[ i%32 ];
        }

        // create RGB image with width and height 
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );

        // ? getting ctext_byte and storing it
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for( int i = 0; i < bf.length; i++ ) bf[i] = ctext_byte[ i ];
        ImageIO.write(img, "png", new File("Text7.png"));


        return ctext_byte;
    }

}