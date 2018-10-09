package com.github.pleier.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * Json 工具类
 *
 * @author : pleier
 * @date : 2018/9/21
 */
public class JsonUtils {

    /**
     * Object转成JSON数据
     *
     * @param object 需要转成json字符串的对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JSON.toJSONString(object);
    }

    /**
     * JSON数据，转成实际对象
     *
     * @param json  json 字符串
     * @param clazz 要转成的对象类型
     * @param <T>   T
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
