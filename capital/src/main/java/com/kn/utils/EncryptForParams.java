package com.kn.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public static class EncryptForParams {

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

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        return  buf.toString();

    }
}
