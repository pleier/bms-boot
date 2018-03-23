package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单
 *
 * @author : pleier
 * @date : 2018/3/23
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     * @return 子菜单列表
     */
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     * @return 子菜单列表
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     *
     * @return 不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 获取用户菜单列表
     *
     * @return 用户菜单列表
     */
    List<SysMenuEntity> getUserMenuList(Long userId);

    /**
     * 删除
     *
     * @param menuId 菜单ID
     */
    void delete(Long menuId);
}
