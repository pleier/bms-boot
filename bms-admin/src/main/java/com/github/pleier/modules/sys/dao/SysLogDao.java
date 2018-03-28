package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author : pleier
 * @date : 2018/3/28
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
}
