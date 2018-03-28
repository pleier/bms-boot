package com.github.pleier.modules.sys.redis;

import com.github.pleier.common.utils.RedisKeys;
import com.github.pleier.common.utils.RedisUtils;
import com.github.pleier.modules.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * redis操作系统配置
 *
 * @author : pleier
 * @date : 2018/3/28
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 将系统配置存入redis
     *
     * @param config
     */
    public void saveOrUpdate(SysConfigEntity config) {
        if (config == null) {
            return;
        }
        String key = RedisKeys.getSysConfigKey(config.getKey());
        redisUtils.set(key, config);
    }

    /**
     * 从redis删除系统配置
     *
     * @param configKey
     */
    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    /**
     * 从redis获取系统配置
     *
     * @param configKey
     * @return
     */
    public SysConfigEntity get(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }
}
