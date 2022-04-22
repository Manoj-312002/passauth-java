package com.auth;
import java.awt.image.*;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class randImage {
    private int width = 400 , height = 100;
    
    byte[] genImage() throws Exception{
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        
        Random rd = new Random();
        byte r_byte [] = new byte[bf.length];
        rd.nextBytes(r_byte );

        for( int i = 0; i < bf.length; i++ ) bf[i] = r_byte[i];
        
        ImageIO.write(img, "png", new File("Text4b.png"));
        return r_byte;
    }
}
