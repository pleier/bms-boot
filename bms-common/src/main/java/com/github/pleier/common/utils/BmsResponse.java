package com.github.pleier.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pleier
 * @date
 */
public class BmsResponse extends HashMap<String, Object> {
    private static final long serialVersionUID = 3204450244886257640L;

    public BmsResponse() {
        put("code", 0);
    }

    public static BmsResponse error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static BmsResponse error(String msg) {
        return error(500, msg);
    }

    public static BmsResponse error(int code, String msg) {
        BmsResponse r = new BmsResponse();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static BmsResponse ok(String msg) {
        BmsResponse r = new BmsResponse();
        r.put("msg", msg);
        return r;
    }

    public static BmsResponse ok(Map<String, Object> map) {
        BmsResponse r = new BmsResponse();
        r.putAll(map);
        return r;
    }

    public static BmsResponse ok() {
        return new BmsResponse();
    }

    public static BmsResponse ok(String key, Object value) {
        BmsResponse r = new BmsResponse();
        r.put(key, value);
        return r;
    }

    @Override
    public BmsResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
