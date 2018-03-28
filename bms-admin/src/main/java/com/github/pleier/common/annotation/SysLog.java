package com.github.pleier.common.annotation;

import java.lang.annotation.*;

/**
 * @author : pleier
 * @date : 2018/3/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
