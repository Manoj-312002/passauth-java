package com.auth;
import java.security.*;
import java.util.*; 

public class App {
    private static byte [] xor1;

    public static void main( String[] args ) throws Exception {
        
        /*
            s - seed
            s1 - seed1
            s2 - seed2
            Pc - password client 
            CText - clear text sent from server
            xor1 - xor of CText and h( pc , s ) - T in architecture
            xor1S - string representation of xor1
            xor1_img - byte array of xor1
            rnd_img - random image generated
            unshuffled_img - combination of xor1_img and rnd_img - t' in architecture

            //* - printing statements
            //? - Image generation

            Text1 : clear image
            Text2 : xored image
            Text3 : unxoring Text2 , = Text1
            Text4 : random image
            Text5 : shuffled image
            Text6 : unshuffled image from server
            Text7 : ctext from server

            random image - 400 x 100 x 3
            ctext image - 398 x 24 x 3
            unshuffled image - 164 x 304 x 3
        */
        
        Random rd = new Random(); 
        
        Integer s = rd.nextInt();
        Integer s1 = rd.nextInt();
        Integer s2 = rd.nextInt();

        String Pc = "4443354h";
        sendClient sC = new sendClient();

        byte [] CText = sC.getCtext();
        
        // hashing password
        MessageDigest md = MessageDigest.getInstance("SHA3-256");
        md.update( s.byteValue() );
        byte[] hashed_password = md.digest( Pc.getBytes("utf-8") );
        


        //* printing hashed password
        System.out.println("Hashed Password : ");
        StringBuilder sb = new StringBuilder(); for (byte b : hashed_password )  sb.append(String.format("%02x", b));
        System.out.println(sb.toString());
        System.out.println("\n");

        
        
        // xoring CText and h( pc , s )
        xor1 = CText.clone();
        for( int i = 0; i < 32; i++ ) xor1[i] ^= hashed_password[i];
        

        //* representing xor1 in hex
        System.out.println("xor1 in hex format : ");
        StringBuilder xor1sB = new StringBuilder(); for (byte b : xor1 )  xor1sB.append(String.format("%02x", b));
        String xor1S = xor1sB.toString();
        System.out.println( xor1S + "\n");
        
        // initialising for xoring the image
        MessageDigest md1 = MessageDigest.getInstance("SHA3-256");
        md1.update( s1.byteValue() );
        byte[] hashed_password1 = md1.digest( Pc.getBytes("utf-8") );
        

        
        textToImage tS = new textToImage();    
        // ! need to pass xor1S instead of string
        byte[] xor1_img = tS.convertText( new String( xor1S ) , hashed_password1 );
        tS.reconvert( hashed_password1 );


        // generating random image
        randImage ri = new randImage();
        byte[] rnd_img = ri.genImage();


        MessageDigest md2 = MessageDigest.getInstance("SHA3-256");
        md2.update( s2.byteValue() );
        byte[] hashed_password2 = md2.digest( Pc.getBytes("utf-8") );
        
        // combining rnd_img , xor1_img
        byte [] unshuffled_img = new byte[ rnd_img.length + xor1_img.length ];
        for( int i = 0; i < unshuffled_img.length ; i++ ){
            if( i < rnd_img.length ) unshuffled_img[i] = rnd_img[i];
            else unshuffled_img[i] = xor1_img[i-rnd_img.length];
        }
        
        //* random image and xcor1 image length 
        System.out.println( "Random Image length : " + rnd_img.length + " xor1 image length : " + xor1_img.length + "\n" );
        
        //* printing unshuffled image
        System.out.println("Unshuffled image");
        StringBuilder sb3 = new StringBuilder(); for (byte b : unshuffled_img )  sb3.append(String.format("%02x", b));
        System.out.println(sb3.toString().substring( sb3.length() - 10 )); 
        System.out.println("\n");


        
        embedShuffle eS = new embedShuffle();
        byte[] shuffled_img = eS.shuffle( hashed_password2 , unshuffled_img );
        sC.send(shuffled_img, s, s1, s2);
    }
}
