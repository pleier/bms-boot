package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysRoleDeptEntity;

import java.util.List;

/**
 * 角色与部门对应关系
 *
 * @author : pleier
 * @date : 2018/3/27
 */
public interface SysRoleDeptService extends IService<SysRoleDeptEntity> {

    /**
     * 保存或修改
     *
     * @param roleId     roleId
     * @param deptIdList deptIdList
     */
    void saveOrUpdate(Long roleId, List<Long> deptIdList);

    /**
     * 根据角色ID，获取部门ID列表
     *
     * @param roleIds roleIds
     * @return List<Long>
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds roleIds
     * @return int
     */
    int deleteBatch(Long[] roleIds);

}
