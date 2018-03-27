package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理
 *
 * @author : pleier
 * @date : 2018/3/27
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {
}
