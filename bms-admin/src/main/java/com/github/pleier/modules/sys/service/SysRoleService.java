package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author : pleier
 * @date: 2017/12/11
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存
     *
     * @param role
     */
    void save(SysRoleEntity role);

    /**
     * 修改
     *
     * @param role
     */
    void update(SysRoleEntity role);

    /**
     * 批量删除
     *
     * @param roleIds
     */
    void deleteBatch(Long[] roleIds);
}
