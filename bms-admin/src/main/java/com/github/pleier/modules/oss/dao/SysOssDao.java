package com.github.pleier.modules.oss.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.oss.entity.SysOssEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 *
 * @author : pleier
 * @date : 2018/10/10
 */
@Mapper
public interface SysOssDao extends BaseMapper<SysOssEntity> {
}
