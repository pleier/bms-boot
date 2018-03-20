package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 角色与用户对应关系
 *
 * @author : pleier
 * @date : 2017/11/29
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId
     * @return
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
