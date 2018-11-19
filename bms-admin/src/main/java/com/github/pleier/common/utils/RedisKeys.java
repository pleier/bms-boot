package com.github.pleier.common.utils;

/**
 * 获取key前缀
 *
 * @author : pleier
 * @date : 2018/3/22
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key) {
        return "sessionid:" + key;
    }

    public static String getShiroCacheKey(String key) {
        return "shiroCache:" + key;
    }
}
