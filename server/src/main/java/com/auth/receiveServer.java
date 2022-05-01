package com.auth;


import java.io.*;
import java.net.*;

public class receiveServer {
    ServerSocket ss;
    Socket sc;
    BufferedReader socketin;
    DataInputStream din;

    receiveServer() throws Exception{
        ss = new ServerSocket(9999);
        sc = ss.accept();
        socketin = new BufferedReader( new InputStreamReader( sc.getInputStream() ) );
        din = new DataInputStream( sc.getInputStream() );
    }

    Integer getInt() throws Exception{
        return din.readInt();
    }
    
    byte[] receiveImage() throws Exception{
        // System.out.println( new BigInteger(sh_len).intValue() );
        Integer shuffled_img_ln = din.readInt();
        System.out.println( shuffled_img_ln );
        
        byte[] shuffled_img = new byte[ shuffled_img_ln ];

        din.readFully( shuffled_img );
        return shuffled_img;
    }
}
