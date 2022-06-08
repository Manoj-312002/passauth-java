package com.auth;

public class binaryToString {
    String[] conv = new String[] {
        "a" ,"b" ,"c" ,"d" ,"e" ,"f" ,"g" ,"h" ,"i" ,"j" ,"k" ,"l" ,"m" ,"n" ,"o" ,"p" ,"q" ,"r" ,"s" ,"t" ,"u" ,"v" ,"w" ,"x" ,"y" ,"z" ,
        "A" ,"B" ,"C" ,"D" ,"E" ,"F" ,"G" ,"H" ,"I" ,"J" ,"K" ,"L" ,"M" ,"N" ,"O" ,"P" ,"Q" ,"R" ,"S" ,"T" ,"U" ,"V" ,"W" ,"X" ,"Y" ,"Z" ,
        "!" ,"@" ,"#" ,"$" ,"%" ,"^" ,"&" ,"*" ,"(" ,")" , "<" , ">"
    };

    String convB( byte [] xor1 ){
        String converted = "";
        
        System.out.println( xor1.length );
        for( int i = 0; i < xor1.length ; i++ ){
            byte bt = xor1[i] , bk = 0x0F, fd = (byte)(-16);
            byte msb = (byte)(bt & fd);
            byte lsb = (byte)(bt & bk);
            
            System.out.println( (int)bt  + " : ");
            System.out.println( (int)msb );
            msb = (byte)(msb >> 4);
            System.out.println( Math.abs( (int)msb ) );
            System.out.println( (int)lsb );
            // converted += conv[idx];
        }
        
        return converted;
    }   
}
