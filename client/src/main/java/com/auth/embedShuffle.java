package com.auth;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;


public class embedShuffle {

    private int width = 299 , height = 227;
    
    byte[] shuffle( byte[] hashed_password1 , byte[] unshuffled_img ) throws Exception{
        
        int m = unshuffled_img.length;
        long value = new BigInteger( hashed_password1 ).longValue();

        ArrayList<Integer> idx = new ArrayList<>();
        for( int i = 0; i < m; i++ ) idx.add(i);
        // shuffling the index array based on seed
        Collections.shuffle( idx , new Random( value ));

        
        // shuffling image
        byte [] shuffled_img = new byte[m];
        for( int i = 0; i < m; i++ ) shuffled_img[i] = unshuffled_img[ idx.get(i) ];
        

        
        // ? for printing the example
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for( int i = 0; i < m; i++ ) bf[i] = shuffled_img[i];
        ImageIO.write(img, "png", new File("Text5.png"));


        return shuffled_img;
    }

}
