package com.github.pleier.modules.sys.shiro;

import com.github.pleier.common.utils.JsonUtils;
import com.github.pleier.common.utils.RedisKeys;
import com.github.pleier.common.utils.RedisUtils;
import com.github.pleier.common.utils.SerializeUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro缓存与redis整合
 *
 * @author : pleier
 * @date : 2018/9/25
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private RedisUtils redisUtils;

    public RedisCache(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    private RedisCache() {

    }

    @Override
    public V get(K key) throws CacheException {
        try {
            if (key == null) {
                return null;
            } else {
                String rawValue = redisUtils.get(RedisKeys.getShiroCacheKey(key.toString()));
                if (null == rawValue) {
                    return null;
                }
                @SuppressWarnings("unchecked")
                V value = (V) SerializeUtils.deserialize(JsonUtils.fromJson(rawValue, byte[].class));
                return value;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        redisUtils.set(RedisKeys.getShiroCacheKey(key.toString()), SerializeUtils.serialize(value), 1800L);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previous = get(key);
        redisUtils.delete(key.toString());
        return previous;
    }

    @Override
    public void clear() throws CacheException {
        Set<String> keys = redisUtils.keys(RedisKeys.getShiroCacheKey("*"));
        redisUtils.delete(keys);
    }

    @Override
    public int size() {
        Set<String> keys = redisUtils.keys(RedisKeys.getShiroCacheKey("*"));
        return keys.size();
    }

    @Override
    public Set<K> keys() {
        Set<String> keys = redisUtils.keys(RedisKeys.getShiroCacheKey("*"));
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        } else {
            Set<K> newKeys = new HashSet<>();
            for (String key : keys) {
                newKeys.add((K) key);
            }
            return newKeys;
        }
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
