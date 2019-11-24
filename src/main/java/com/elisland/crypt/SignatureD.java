package com.elisland.crypt;

import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/14
 * @Description:
 */
public class SignatureD {
    public static final String SHA256RSA = "SHA256withRSA";

    public static void main(String[] args) throws Exception {
        String message = "gr";
        byte[] s = sign(message);
        System.out.println(s);

        /*******校验********/
        verify(message, s);

    }

    /**
     * 验证
     * @param message
     * @param s
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    private static void verify(String message, byte[] s) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //1.初始化签名对象
        Signature signature = Signature.getInstance(SHA256RSA);
        //2.初始化校验
        signature.initVerify((PublicKey)CreateKey().get("publicKey"));
        //3.传入原文
        signature.update(message.getBytes());
        //4.校验
        boolean verify = signature.verify(s);
        System.out.println(verify);
    }

    /**
     * 签名
     *
     * @param message
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    private static byte[] sign(String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //1.创建签名对象
        Signature signature = Signature.getInstance(SHA256RSA);
        //2.初始签名 (传入私钥)
        signature.initSign((PrivateKey)CreateKey().get("privateKey"));
        //3.传入原文
        signature.update(message.getBytes());
        //4.签名
        byte[] sign = signature.sign();
        byte[] encode = Base64.getEncoder().encode(sign);
        return sign;
    }

    private static Map<String, Object> CreateKey() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        Map<String, Object> map = new HashMap<>();
        map.put("privateKey", privateKey);
        map.put("publicKey", publicKey);
        return map;
    }


}
