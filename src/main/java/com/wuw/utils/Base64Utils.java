package com.wuw.utils;

import java.util.Base64;

public class Base64Utils {

    public static String encodeToString(String bs){
        return Base64.getEncoder().encodeToString(bs.getBytes());
    }

    public static String encodeToString(byte[] bs){
        return Base64.getEncoder().encodeToString(bs);
    }

    public static byte[] decode(String string){
        return Base64.getDecoder().decode(string);
    }

}
