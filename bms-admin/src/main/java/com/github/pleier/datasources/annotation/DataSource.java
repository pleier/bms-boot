package com.github.pleier.datasources.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author : pleier
 * @date : 2018/3/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String name() default "";
}
