package com.github.pleier.modules.sys.shiro;

import com.github.pleier.common.utils.RedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 整合redis与shiroCache
 *
 * @author : pleier
 * @date : 2018/10/11
 */
public class RedisCacheManager implements CacheManager {

    @Autowired
    private RedisUtils redisUtils;

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache c = caches.get(name);

        if (c == null) {
            // create a new cache instance
            c = new RedisCache<K, V>(redisUtils);
            // add it to the cache collection
            caches.put(name, c);
        }
        return c;
    }
}
