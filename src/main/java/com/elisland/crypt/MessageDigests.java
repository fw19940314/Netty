package com.elisland.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/14
 * @Description:
 */
public class MessageDigests {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String a  = "hello 好！";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(a.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for (byte s : digest){
            int hex = s & 0xff;
            String s1 = Integer.toHexString(hex);
            stringBuffer.append(s1);
        }
        String s = new String(digest);
        System.out.println(s);
        System.out.println(stringBuffer);
    }
}
