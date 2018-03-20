package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色与部门对应关系
 *
 * @author : pleier
 * @date: 2017/12/11
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {


    /**
     * 根据角色ID，获取部门ID列表
     *
     * @param roleId
     * @return 部门ID列表
     */
    List<Long> queryDeptIdList(Long[] roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
