package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author : pleier
 * @date : 2018/3/27
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 保存或修改
     *
     * @param userId     用户ID
     * @param roleIdList 角色ID列表
     */
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户ID
     * @return List<Long>
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds 角色ID数组
     * @return int
     */
    int deleteBatch(Long[] roleIds);
}
