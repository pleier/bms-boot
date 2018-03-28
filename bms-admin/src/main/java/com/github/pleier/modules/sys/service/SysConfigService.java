package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysConfigEntity;

import java.util.Map;

/**
 * 系统配置
 *
 * @author : pleier
 * @date : 2018/3/28
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    /**
     * 分页查询
     *
     * @param params params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存配置信息
     *
     * @param config config
     */
    void save(SysConfigEntity config);

    /**
     * 更新配置信息
     *
     * @param config
     */
    void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     *
     * @param key   key
     * @param value value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     *
     * @param ids ids
     */
    void deleteBatch(Long[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key key
     */
    String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     * @return T
     */
    <T> T getConfigObject(String key, Class<T> clazz);
}
