package com.github.pleier.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author : pleier
 * @date : 2018/3/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
