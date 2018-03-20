package com.github.pleier.common.validator;

import com.github.pleier.common.exception.BmsException;
import org.apache.commons.lang.StringUtils;

/**
 * 数具校验
 *
 * @author : pleier
 * @date: 2017/12/8
 */
public abstract class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BmsException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BmsException(message);
        }
    }
}
