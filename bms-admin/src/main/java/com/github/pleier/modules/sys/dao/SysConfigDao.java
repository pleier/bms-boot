package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置
 *
 * @author : pleier
 * @date : 2018/3/28
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
     * 根据key，查询value
     *
     * @param paramKey paramKey
     * @return SysConfigEntity
     */
    SysConfigEntity queryByKey(String paramKey);

    /**
     * 根据key，更新value
     *
     * @param key   key
     * @param value value
     * @return int
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
