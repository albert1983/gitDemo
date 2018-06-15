package com.zhuoyue.common.log.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截servicer，打印日志
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

    String description() default "";


}