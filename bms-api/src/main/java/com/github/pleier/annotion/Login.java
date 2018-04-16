package com.github.pleier.annotion;

import java.lang.annotation.*;

/**
 * 登录校验
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
