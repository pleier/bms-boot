package com.github.plei.bms.modules.sys.redis;

import com.github.plei.bms.modules.sys.entity.SysConfigEntity;
import com.github.plei.common.utils.RedisKeys;
import com.github.plei.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 系统配置redis
 *
 * @author : pleier
 * @date: 2018/1/8
 */
@Component("sysConfigRedis")
public class SysConfigRedis {
    @Autowired
    @Qualifier("redisUtils")
    private RedisUtils redisUtils;

    /**
     * 保存
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
     * 删除
     *
     * @param configKey
     */
    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisUtils.delete(key);
    }

    /**
     * 获取
     *
     * @param configKey
     * @return
     */
    public SysConfigEntity get(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        return redisUtils.get(key, SysConfigEntity.class);
    }
}
