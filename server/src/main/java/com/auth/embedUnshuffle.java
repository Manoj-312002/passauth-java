package com.auth;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

public class embedUnshuffle {

    private int width = 304 , height = 200;

    byte[] unshuffle( byte[] hashed_password2 , byte[] shuffled_img ) throws Exception{
        
        int m = shuffled_img.length;
        long value = new BigInteger( hashed_password2 ).longValue();

        ArrayList<Integer> idx = new ArrayList<>();
        for( int i = 0; i < m; i++ ) idx.add(i);
        Collections.shuffle( idx , new Random( value ));
        byte [] unshuffled_img = new byte[m];
        
        for( int i = 0; i < m; i++ )
            unshuffled_img[idx.get(i)] = shuffled_img[i];
        
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR );
        byte bf[] = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();

        
        // ? for printing the example
        for( int i = 0; i < m; i++ ) bf[i] = unshuffled_img[i];
        ImageIO.write(img, "png", new File("Text6.png"));

        return unshuffled_img;


    }
}