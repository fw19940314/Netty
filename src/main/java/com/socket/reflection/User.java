package com.socket.reflection;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/15
 * @Description:
 */
//@Fileds("CS")
public class User {
    /**
     *
     */
    private String name = "1a";

    /**
     *
     */
    public String age;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
