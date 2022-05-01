package com.auth;


public class App {
    public static void main( String[] args ) throws Exception{
        receiveServer rs = new receiveServer();
        
        byte[] shuffled_img = rs.receiveImage();
        Integer s = rs.getInt();
        Integer s1 = rs.getInt();
        Integer s2 = rs.getInt();

        StringBuilder sb = new StringBuilder(); for (byte b : shuffled_img )  sb.append(String.format("%02x", b));
        System.out.println(sb.toString()); 

        String Pc = "4443354h";
        
    }
}
