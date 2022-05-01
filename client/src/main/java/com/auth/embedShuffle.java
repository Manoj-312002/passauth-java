package com.auth;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;


public class embedShuffle {
    
    byte[] shuffle( byte[] hashed_password1 , byte[] unshuffled_img ) throws Exception{
        int m = unshuffled_img.length;
        long value = new BigInteger( hashed_password1 ).longValue();

        ArrayList<Integer> idx = new ArrayList<>();
        for( int i = 0; i < m; i++ ) idx.add(i);
        Collections.shuffle( idx , new Random( value ));
        // System.out.println( idx );

        byte [] shuffled_img = new byte[m];
        
        for( int i = 0; i < m; i++ )
            shuffled_img[i] = unshuffled_img[ idx.get(i) ];
        
        
        BufferedImage img = new BufferedImage(301, 145, BufferedImage.TYPE_3BYTE_BGR );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        // System.out.println( shuffled_img.length , bf.length );

        for( int i = 0; i < m; i++ ) bf[i] = shuffled_img[i];
        ImageIO.write(img, "png", new File("Text5.png"));
        return shuffled_img;
    }

}
