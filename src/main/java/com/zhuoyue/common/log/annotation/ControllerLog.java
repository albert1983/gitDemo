package com.zhuoyue.common.log.annotation;


import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller ，打印日志
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

    //操作标识，用来表明这个方法是做什么的，会保存在日志里
    String description() default "";

}
