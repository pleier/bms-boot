package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author : pleier
 * @date: 2017/12/11
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity>{

    /**
     * 保存或者修改
     *
     * @param roleId
     * @param menuIdList
     */
    void saveOrUpdate(Long roleId, List<Long> menuIdList);


    /**
     * 根据角色ID，获取菜单ID列表
     *
     * @param roleId
     * @return
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
