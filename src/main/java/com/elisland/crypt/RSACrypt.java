package com.elisland.crypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/14
 * @Description:
 */
public class RSACrypt {
    public static final String ALGORITHM = "RSA";
    public static final int ENCRYPT_MAX_LENGTH = 117;//每次最大加密长度
    public static final int DECRYPT_MAX_LENGTH = 128;//每次最大解密长度

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String message = "你好！！！你好！！！你好！！！你好！！！你好！！！你好！！！你好！！！你好！！！你好！！！";
        //秘钥对生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //秘钥对对象
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyValue = Base64.getEncoder().encode(privateKeyEncoded);
        byte[] publicKeyValue = Base64.getEncoder().encode(publicKeyEncoded);
        System.out.println("privateKeyEncoded:" + new String(privateKeyValue));
        System.out.println("publicKeyEncoded:" + new String(publicKeyValue));
        String privateKeyValues = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC0ntzFBkd/nQvQasTBYZkZOar4IUzNmaSzq1oTn/PxtojjrHCYkIVeULiePSIWe1/sGzS7HhmymQ1xXZf8Z9uUkMUyOoXTx5kxroyS5+s2m1DX52tRD3VXrTK820Y80yFv1xoblwd3uPpJHHRIs+EUNs75doxM7EqJU4gjZB4XjFy3m2LiPp340QdgAiqHoy+iCEEE1G2yVzlUA92GD+WG0/2MfuqrHq0hi2UxHSeAnmFLi27EX4DGDSx7sOHFyzjjFeW1z6yPsfh+6gBfPZs18Zl+tGTcWL2ubaZa8yHqyvROl47FvMp2E2jcz77HSocXeWjQpeOMlHQCYNJPeWQbAgMBAAECggEARpyYqHn098HYvYkQFJ3oMYFnAoxaL0Kyrf7+Pw/YWZXH3GIsvqg5S475LfuuWOWwsWhNbB2vZDALnlc9tNKReHpEbYeNjCbLEC4yDLiYLwf3SgDR5qXJdRZj8Is04VCVENkh/slDhR2O04hJOacBBlsK/WHIJcZ2uUsrvNJGJWP1GNqggQHFthZlIuIalX1L2k5mXKlxCEy3KnZrcPRp5PZNenLLa0xToDjYtxtKLid1+1J6dapeMsWR7TXsWmzm/vb+MnUz85e2z7DJGFviL2EAHgPpsMyDMbty9c+lT7T14j8OQrvhFIs/vCYY3Mp29kSqfORYPZrEVeh1JrrbmQKBgQDfCtO63OvvB0xOfXsxYSR9M8X7bzgZI1lAJLUp8Al11gd22CThf1Z2FUj4WUjQj4Y+2zykYu/8cHgvxfDQr1jg9WrDNtQpHeP4Ex6b7hYWPtPLibFCI0e8UcOvcoKIzV2svW6oNZWkxgTViTZbo68kRuJB7nHB8I7mb697i30ZZQKBgQDPT1G/3Wm3oLGVSpHKKbk0oLwlKQ/J5RgOrOYHQO+3aHTQUQzpEQGwSv5IHRpCZ3iDnHT7PTUBnDJFaZquiPhb/IQ0fysXIB1z0oY2FB7zc7jsHLgo+wzeaH9utQQV/S48QRYH8qQG0Z25q875Ix02Q5b+lMJDbBdHcZynwdtvfwKBgDchTvpmNu8LJ8JS48ttzn4f+PTQJdaY1Su0pw7ppPz6XTfz7xHJzs47LqEsOIJMp4sL165uQhFsn1h3nbGHVfmsz6nW+JP9LmcYGr3CGLWA7AroSuclH0seDrdkH32+/UOAQ0TSkd17kKBTXRtDlzvpRW20iWu9kSTGQGLSNKa9AoGAM+pNMpskClQ3pIdUEyh3tPI7q6KozYqNh79Mq6xbW7bQ6goiDYuwvJFLNIv1M4Pt7ieUNAaT659OB4v2rJaoe4C1KuBdziDAv2yNJ6XKSnVEbfyTMdAM3PWWVAcIyujoVzaFaeAdq4d61DBuWH2upxQuD/r42yGKgt20uV4WsckCgYALsSowRtv5zdYpI0lh4PQDH3X/wWZ8Q1cRN5OLxcVF09s4Q9mmGWN/jE9SQAO6psOL70FZ/C0QXktlWmuRWg0lEh/SWZJZxmrPf8zURUlUkX+PF6p0T+FwjKyHFMYHo2CWxj9t0yhag6pj/7WKRw8oTDF43fkn5fja9J5NH9qzsQ==";
        String publicKeyValues = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoHgFzUFr3DvCCkyaIC5d4fQ/H3KxF218Z/o1tG9BpItAtsJIfK+i1xm2QGXVgNOf62xFv0PoJNjYvZAmjZZ30wJUH36Ym3LUpjyfFq758MhyZq+KCk8pvhwmOQ/wbm91n/OvasrMZosXi6Ubt6N/dwl9XUlhP44noDdPudYxjWZ8Rg0wk+8E1gmGVy2L7qFfW+fEoEm6DMPvfzWA6HYUe0dOho3oruklzXgRG36ZQh9kcvjAU6IyOj2XsKKTnMJxrxWc1zBaCzPiiEF8woGn71b0VNwmNfX3JOmr9hcrixP5F2t307THIXSQVeqyhS97cJqZaMF/NeqdAokuh9poTQIDAQAB";
        //生成秘钥对：公钥、私钥
//        String s1 = publicKeyEncrypt(publicKey, message);
//        System.out.println("加密：" + s1);
//        //解密
//        String s = publicKeyDecrypt(privateKey, s1);
//        System.out.println("解密：" + s);


        /********************创建私钥公钥（保存）*********************/
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//        PrivateKey privateKey1 = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyValues.getBytes()));
        PublicKey publicKey1 = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyValues.getBytes()));

//        System.out.println(privateKey1);
        System.out.println(publicKey1);

    }

    //公钥加密
    private static String publicKeyEncrypt(PublicKey publicKey, String message) {
        try {
            //Cipher
            //1.创建cipher对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //2.初始化加密解密模式
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //3.加密解密
//            byte[] bytes = cipher.doFinal(message.getBytes());


            /*------------------------------------------------------------*/
            //拓展 RSA最大解密长度128，如果字符段长度过长，可采取分段解密的方式
            int offset = 0;//偏移量
            byte[] buffer = new byte[1024];//存储加密后数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (message.getBytes().length - offset > 0) {
                if (message.getBytes().length - offset >= ENCRYPT_MAX_LENGTH) {
                    //使用含有分段加密的方式
                    buffer = cipher.doFinal(message.getBytes(), offset, ENCRYPT_MAX_LENGTH);
                    offset += ENCRYPT_MAX_LENGTH;
                } else {//剩下最后一块时（不足117的长度）
                    //使用含有分段加密的方式
                    buffer = cipher.doFinal(message.getBytes(), offset, message.getBytes().length - offset);
                    offset = message.getBytes().length;
                }
                byteArrayOutputStream.write(buffer);//将数据写入
            }
            /*------------------------------------------------------------*/
//            byte[] bytes = byteArrayOutputStream.toByteArray();
            byte[] encode1 = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
            String s = new String(encode1);
            System.out.println(s);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //私钥解密
    private static String publicKeyDecrypt(PrivateKey privateKey, String message) {
        byte[] decode = Base64.getDecoder().decode(message);
        try {
            //Cipher
            //1.创建cipher对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //2.初始化加密解密模式
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //3.解密（分段）
            int offset = 0;//偏移量
            byte[] buffer = new byte[1024];//存储加密后数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (decode.length - offset > 0) {
                if (decode.length - offset >= DECRYPT_MAX_LENGTH) {
                    //使用含有分段加密的方式
                    buffer = cipher.doFinal(decode, offset, DECRYPT_MAX_LENGTH);
                    //重新计算偏移量
                    offset += DECRYPT_MAX_LENGTH;
                } else {//剩下最后一块时（不足128的长度）
                    //使用含有分段加密的方式
                    buffer = cipher.doFinal(decode, offset, decode.length - offset);
                    offset = decode.length;
                }
                byteArrayOutputStream.write(buffer);//将数据写入
            }
            String s = new String(byteArrayOutputStream.toByteArray());
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
