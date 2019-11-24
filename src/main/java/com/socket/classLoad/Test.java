package com.socket.classLoad;

import com.socket.reflection.User;
import com.sun.org.apache.xpath.internal.operations.String;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/17
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello wprld");
    }
//    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
////        Class<User> userClass = User.class;
////        Method setName = userClass.getDeclaredMethod("setName", String.class);
////        System.out.println(setName.invoke(new String(),"1"));
//    }
}
