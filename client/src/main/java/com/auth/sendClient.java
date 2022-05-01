package com.auth;

import java.io.*;
import java.net.Socket;

public class sendClient {
    Socket s;
    BufferedReader socketin;
    OutputStream socketout;
    ByteArrayOutputStream baos;
    DataOutputStream dos;

    sendClient() throws Exception{
        s = new Socket( "localhost" , 9999 );
        socketin = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
        socketout = s.getOutputStream();
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
    }
    void send( byte[] shuffled_img , Integer s , Integer s1 , Integer s2 ) throws Exception{
        
        System.out.println( shuffled_img.length );
        
        // length for buffer initialisation and send image
        dos.writeInt( shuffled_img.length );
        dos.write( shuffled_img );

        // all three seeds
        dos.writeInt( s );
        dos.writeInt( s1 );
        dos.writeInt( s2 );
        
        // StringBuilder sb = new StringBuilder(); for (byte b : shuffled_img )  sb.append(String.format("%02x", b));
        // System.out.println(sb.toString());
        
        System.out.println( s + " " + s1 + " " + s2  );
        socketout.write( baos.toByteArray() );
    }
}
