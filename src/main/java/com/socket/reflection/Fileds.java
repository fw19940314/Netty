package com.socket.reflection;

import java.lang.annotation.*;

/**
 * @Auther: jerry.feng
 * @Date: 2019/7/15
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Fileds {
    String value();
}
