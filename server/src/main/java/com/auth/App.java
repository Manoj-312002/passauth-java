package com.auth;
import java.security.MessageDigest;
import java.util.*; 

public class App {
    public static void main( String[] args ) throws Exception{
        receiveServer rs = new receiveServer();
        
        // CText to be sent to server
        byte[] CText = UUID.randomUUID().toString().replaceAll("-", "" ).getBytes();
        rs.sendCText(CText);

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
    }
}