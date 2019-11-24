package com.elisland;

import com.elisland.protobuf.Student;
import com.google.gson.JsonArray;
import com.google.protobuf.InvalidProtocolBufferException;

import javax.swing.*;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

/**
 * @Auther: jerry.feng
 * @Date: 2019/6/15
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException, SocketException, KeyStoreException, NoSuchAlgorithmException {
//        Student.Person person = Student.Person
//                .newBuilder()
//                .setName("jerry")
//                .setId(100)
//                .setEmail("2019@gmial.com")
//                .build();
//        System.out.println("before person=>\n"+person);
//
//        byte[] personBytes = person.toByteArray();
//        //字节数组解析为对象
//        Student.Person person1 = Student.Person.parseFrom(personBytes);
//        System.out.println("after person=>"+person1);
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        byte[] value = "123".getBytes();
////        buffer.putInt(value.length);
////
//        buffer.put(value);
//        buffer.flip();
//        System.out.println(buffer.get(1));
//        buffer.putChar('你');
//        buffer.putInt(1024);
//        buffer.flip();
//        value = null;
////        byte[] result = new byte[buffer.remaining()];
//        System.out.println(buffer.getInt());
//        System.out.println(buffer.get());
//        System.out.println(buffer.getChar());
//        System.out.println(buffer.getInt());
//        String str = "abcde";
//
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//
//        buf.put(str.getBytes());
//
//        buf.flip();
//
//        System.out.println(buf.get(1));

//        byte[] dst = new byte[buf.limit()];
//        System.out.println(dst);
//        buf.get(dst, 0, 2);
//        System.out.println(new String(dst, 0, 2));
//        System.out.println(buf.position());
//
//        //mark() : 标记
//        buf.mark();
//
//        buf.get(dst, 2, 2);
//        System.out.println(new String(dst, 2, 2));
//        System.out.println(buf.position());

        //reset() : 恢复到 mark 的位置
//        buf.reset();
//        System.out.println(buf.position());
//
//        //判断缓冲区中是否还有剩余数据
//        if(buf.hasRemaining()) {
//
//            //获取缓冲区中可以操作的数量
//            System.out.println(buf.remaining());
//        byte b = (byte) 0Xff;
//        int a = 0Xff;
//        byte b1 = (byte) 0XFF;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(b1);
//        System.out.println(1<<2);
//        System.out.println(Integer.parseInt("0100", 2));

//        User user = new Test.User();
//        user.setId(123);
//        user.setName("jerry");
        // 获得本机的所有网络接口
//        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
////
////        while (nifs.hasMoreElements()) {
////            NetworkInterface nif = nifs.nextElement();
////
////            // 获得与该网络接口绑定的 IP 地址，一般只有一个
////            Enumeration<InetAddress> addresses = nif.getInetAddresses();
////            while (addresses.hasMoreElements()) {
////                InetAddress addr = addresses.nextElement();
////
////                if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
////                    System.out.println("网卡接口名称：" + nif.getName());
////                    System.out.println("网卡接口地址：" + addr.getHostAddress());
////                    System.out.println();
////                }
////            }
////        }
//        byte[] b = "123".getBytes();
//        BigInteger bigInteger = new BigInteger(1, b);
//        System.out.println(bigInteger);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        MessageDigest.getInstance("MD5");
        System.out.println(keyStore);

    }


    static class User {
        /**
         *
         */
        private int id;

        /**
         *
         */
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


