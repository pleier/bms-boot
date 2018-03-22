package com.github.pleier.common.utils;

/**
 * @author : pleier
 * @date : 2018/3/22
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key){
        return "sessionid:" + key;
    }
}
