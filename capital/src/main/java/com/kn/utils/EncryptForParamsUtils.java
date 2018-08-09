package com.kn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public  class EncryptForParamsUtils {

    public  static String  mdEncrypt(String s){


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update( s.getBytes());

        byte b[] = md.digest();

        int i;

        StringBuilder buf = new StringBuilder();
        for (byte aB : b) {
            i = aB;
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return  buf.toString();

    }
}
