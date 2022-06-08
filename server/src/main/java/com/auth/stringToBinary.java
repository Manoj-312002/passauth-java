package com.auth;

import java.util.HashMap;
import java.util.Map;

public class stringToBinary {
    String[] conv = new String[] {
        "a" ,"b" ,"c" ,"d" ,"e" ,"f" ,"g" ,"h" ,"i" ,"j" ,"k" ,"l" ,"m" ,"n" ,"o" ,"p" ,"q" ,"r" ,"s" ,"t" ,"u" ,"v" ,"w" ,"x" ,"y" ,"z" ,
        "A" ,"B" ,"C" ,"D" ,"E" ,"F" ,"G" ,"H" ,"I" ,"J" ,"K" ,"L" ,"M" ,"N" ,"O" ,"P" ,"Q" ,"R" ,"S" ,"T" ,"U" ,"V" ,"W" ,"X" ,"Y" ,"Z" ,
        "!" ,"@" ,"#" ,"$" ,"%" ,"^" ,"&" ,"*" ,"(" ,")" , "<" , ">"
    };

    Map<String, Integer> convM;
    byte [] convS( String s ){
        convM = new HashMap<>();
        for( int i = 0; i < 64 ; i++ ){
            convM.put( conv[i] , i );
        }
        byte xor1[] = new byte[32];
        for( int i = 0; i < s.length() ; i++ ){
            xor1[i] = (byte)s.charAt(i);
        }
        return xor1;    
    }   
}
