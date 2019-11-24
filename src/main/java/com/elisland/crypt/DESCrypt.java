package com.elisland.crypt;

import io.netty.util.CharsetUtil;
import sun.text.resources.en.FormatData_en_GB;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.print.DocFlavor;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.KeySpec;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/13
 * @Description: 1.创建cipher对象；
 * 2.
 */
public class DESCrypt {
    public static final String ALGORITHM = "DES";//算法

    public static void main(String[] args) throws UnsupportedEncodingException {
        //加密算法核心类Cipher
        String message = "hello cipher!";
        String password = "12345678";
//        byte[] encrypt = Encrypt(message, password);
//        byte[] decrypt = Decrypt(encrypt, password);
//        System.out.println(new String(decrypt));

        String a = "冯";
        System.out.println(a.getBytes().length);
        for (int i = 0; i < a.getBytes(CharsetUtil.US_ASCII).length; i++) {
            byte b1 = a.getBytes()[i];
            System.out.println(b1);
        }

        char b = '冯';
        System.out.println(((byte) (b)));

    }

    //加密
    private static byte[] Encrypt(String message, String password) {
        try {
            //1.创建cipher对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //秘钥工厂
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new DESKeySpec(password.getBytes());
            Key key = skf.generateSecret(keySpec);
            //2.初始化模式，加密/解密
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //3.加密、解密
            byte[] bytes = cipher.doFinal(message.getBytes());
            System.out.println(new String(bytes, CharsetUtil.UTF_8));
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    private static byte[] Decrypt(byte[] bytes, String password) {
        try {
            //1.创建cipher对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //秘钥工厂
            SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new DESKeySpec(password.getBytes());
            Key key = skf.generateSecret(keySpec);
            //2.初始化模式，加密/解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            //3.加密、解密
            byte[] bytes1 = cipher.doFinal(bytes);
            return bytes1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
