package com.auth;


import java.io.*;
import java.net.*;

public class receiveServer {
    ServerSocket ss;
    Socket sc;
    BufferedReader socketin;
    OutputStream socketout;
    ByteArrayOutputStream baos;
    DataOutputStream dos;
    DataInputStream din;

    receiveServer() throws Exception{
        ss = new ServerSocket(9999);
        sc = ss.accept();
        socketin = new BufferedReader( new InputStreamReader( sc.getInputStream() ) );
        socketout = sc.getOutputStream();
        baos = new ByteArrayOutputStream();
        dos = new DataOutputStream(baos);
        din = new DataInputStream( sc.getInputStream() );
    }

    // to read seed values
    Integer getInt() throws Exception{
        return din.readInt();
    }

    // starting server communication by sending ctext
    void sendCText( byte[] CText ) throws Exception{
        dos.writeInt( CText.length );
        dos.write( CText );
        socketout.write( baos.toByteArray() );
    }

    // get the shuffled image
    byte[] receiveImage() throws Exception{
        
        Integer shuffled_img_ln = din.readInt();
        byte[] shuffled_img = new byte[ shuffled_img_ln ];

        din.readFully( shuffled_img );
        return shuffled_img;
    }
}
