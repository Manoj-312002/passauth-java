package com.auth;

import java.io.*;
import java.net.Socket;

public class sendClient {
    Socket s;
    BufferedReader socketin;
    OutputStream socketout;
    ByteArrayOutputStream baos;
    DataOutputStream dos;
    DataInputStream din;

    sendClient() throws Exception{
        s = new Socket( "localhost" , 9999 );
        socketin = new BufferedReader( new InputStreamReader( s.getInputStream() ) );
        socketout = s.getOutputStream();
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        din = new DataInputStream( s.getInputStream() );
    }
    void send( byte[] shuffled_img , Integer s , Integer s1 , Integer s2 ) throws Exception{
        
        
        // length for buffer initialisation and send image
        dos.writeInt( shuffled_img.length );
        dos.write( shuffled_img );

        // all three seeds
        dos.writeInt( s );
        dos.writeInt( s1 );
        dos.writeInt( s2 );
        
        // * prinnting seeds
        System.out.println("Seeds : ");
        System.out.println( s + " " + s1 + " " + s2  );
        System.out.println("\n");

        socketout.write( baos.toByteArray() );
    }

    byte[] getCtext() throws Exception{
        Integer CText_ln = din.readInt();
        byte[] CText = new byte[ CText_ln ];
        din.readFully( CText );
        return CText;
    }

}
