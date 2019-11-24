package com.elisland.crypt;

import io.netty.util.CharsetUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/13
 * @Description:
 */
public class AESCrypt {
    public static final String ALGORITHM = "AES";

    public static void main(String[] args) {
        String message = "你好！";
        String password = "1234567812345678";

        byte[] encrypty = encrypty(message, password);

        byte[] decrypty = decrypty(encrypty, password);
        System.out.println(new String(decrypty, CharsetUtil.UTF_8));
    }
    //加密
    private static byte[] encrypty(String message, String password) {
        try {
            //1.创建cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //通过秘钥工厂，没有AES; 可以通过秘钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), ALGORITHM);
            //2.初始化加密解密方式
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            //3.加密、解密操作
            byte[] bytes = cipher.doFinal(message.getBytes());
            System.out.println(new String(bytes));
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    private static byte[] decrypty(byte[] bytes, String password) {
        try {
            //1.创建cipher
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //通过秘钥工厂，没有AES; 可以通过秘钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), ALGORITHM);
            //2.初始化加密解密方式
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            //3.加密、解密操作
            byte[] bytes1 = cipher.doFinal(bytes);
            return bytes1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
