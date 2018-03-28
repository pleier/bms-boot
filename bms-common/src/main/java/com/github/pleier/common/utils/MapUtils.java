package com.github.pleier.common.utils;

import java.util.HashMap;

/**
 * map工具类
 *
 * @author : pleier
 * @date : 2018/3/20
 */
public class MapUtils extends HashMap<String, Object> {
    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
