package com.socket.reflection;

import com.elisland.thrift.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/15
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<User> userClass = User.class;
//        Field age = userClass.getDeclaredField("name");
//        System.out.println(age.isAccessible());
//        System.out.println("name:"+age.toString());
        Method getName = userClass.getMethod("getName");
//        Annotation annotation = getName.getAnnotation();
//        Class<?> declaringClass = getName.getDeclaringClass();
//        System.out.println(declaringClass.getAnnotatedSuperclass());
//        System.out.println(declaringClass.getName());
//        Method[] methods = declaringClass.getMethods();
//
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//        System.out.println(declaringClass.getMethods());
//
//
//        Fileds annotation = declaringClass.getAnnotation(Fileds.class);
//        System.out.println(annotation.value());
//        System.out.println(aClass);
//        Class<User> userClass = User.class;
////        Field setName = userClass.getField("name");
//        Field name = userClass.getDeclaredField("name");
//        name.setAccessible(true);
//        System.out.println(name.get(User.class.newInstance()));
////        setName.setAccessible(true);
////        System.out.println(setName.invoke(User.class.newInstance(),"1"));
//        ClassLoader classLoader = userClass.getClassLoader();
//        System.out.println(classLoader);
//        Map<String,Object> map = new LinkedHashMap<>();
//        List<Object> list = new ArrayList<>();
//        map.put("1",new Person());
//        map.put("2", Arrays.asList("1","2"));
//        map.put("3",1);
//        list.add(map.values());
//        System.out.println(list.toString());

    }

//    @org.junit.Test
//    public void test() throws IllegalAccessException, InstantiationException {
////        User user1 = new User();
//        Class<User> userClass = User.class;
//        User user = userClass.newInstance();
//        System.out.println(user);
//    }
}
