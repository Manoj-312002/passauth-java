package com.auth;
import java.security.*;
import java.util.*; 

public class App {
    private static byte [] xor1;

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
        
        // initialising for xoring the image
        textToString tS = new textToString(xor1);
        
        tS.convertText( xor1S );
        tS.reconvert();

    }
}
