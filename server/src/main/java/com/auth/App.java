package com.auth;
import java.security.MessageDigest;
import java.util.*; 

public class App {
    public static void main( String[] args ) throws Exception{
        receiveServer rs = new receiveServer();
        
        // CText to be sent to server
        String smp_uuid = UUID.randomUUID().toString().replaceAll("-", "" );
        byte[] CText = smp_uuid.getBytes();
        rs.sendCText(CText);

        // * Printing uuid
        System.out.println("UUID : ");
        System.out.println( smp_uuid  );
        System.out.println("\n");


        
        byte[] shuffled_img = rs.receiveImage();
        Integer s = rs.getInt();
        Integer s1 = rs.getInt();
        Integer s2 = rs.getInt();

        // * prinnting seeds
        System.out.println("Seeds : ");
        System.out.println( s + " " + s1 + " " + s2  );
        System.out.println("\n");

        String Pc = "4443354h";

        //Generating Hashed password with seed 2
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        md.update( s2.byteValue() );
        byte[] hashed_password2 = md.digest( Pc.getBytes("utf-8") );

        embedUnshuffle us = new embedUnshuffle();
        byte[] unshuffled_img = us.unshuffle( hashed_password2 , shuffled_img );
        
        // * unshuffled image printing
        System.out.println("unshuffled image : ");
        StringBuilder sb = new StringBuilder(); for (byte b : unshuffled_img )  sb.append(String.format("%02x", b));
        System.out.println(sb.toString().substring( sb.length() - 10 ) ); 
        System.out.println("\n");

        Integer rnd_img_len = 400*100*3;
        Integer xor1_img_len = unshuffled_img.length - rnd_img_len;

        // extracting the xor1_img from unshuffled image
        byte[] xor1_img = new byte[ xor1_img_len ];
        for( int i = rnd_img_len ; i < unshuffled_img.length ; i++ ){
            xor1_img[i - rnd_img_len] = unshuffled_img[i ];
        } 

        // hashed password 1 generation
        MessageDigest md1 = MessageDigest.getInstance("SHA3-256");
        md1.update( s1.byteValue() );
        byte[] hashed_password1 = md1.digest( Pc.getBytes("utf-8") );
        
        byteToImage bim = new byteToImage();
        byte[] ctext_byte = bim.convertToImage( xor1_img, hashed_password1 );
        
        //* ctext byte printing
        System.out.println("CTEXT byte info : ");
        System.out.println( ctext_byte + "\n");
        

        OCRImplementation ocr = new OCRImplementation();
        String xor1s = ocr.convertImage();

        byte[] xor1 = new byte[xor1s.length() / 2];
        for (int i = 0; i < xor1.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(xor1s.substring(index, index + 2), 16);
            xor1[i] = (byte) j;
        }

        MessageDigest md2 = MessageDigest.getInstance("SHA3-256");
        md2.update( s.byteValue() );
        byte[] hashed_password = md2.digest( Pc.getBytes("utf-8") );
        
        byte[] CText_verify = xor1.clone();
        for( int i = 0; i < 32; i++ ) CText_verify[i] ^= hashed_password[i];

        Boolean ok = true;
        for(int i = 0; i < 32; i++){
            if( CText[i] != CText_verify[i] ) ok = false;
        }

        System.out.println(ok);
    }
}