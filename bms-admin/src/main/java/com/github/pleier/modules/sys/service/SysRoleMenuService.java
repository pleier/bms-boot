package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author : pleier
 * @date : 2018/3/27
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

    /**
     * 修改或更新
     *
     * @param roleId     角色ID
     * @param menuIdList 菜单列表
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId 角色ID
     * @return List<Long>
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     *
     * @param roleIds 角色ID数组
     * @return int
     */
    int deleteBatch(Long[] roleIds);
}
