package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 *
 * @author : pleier
 * @date : 2018/4/12
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {
}
