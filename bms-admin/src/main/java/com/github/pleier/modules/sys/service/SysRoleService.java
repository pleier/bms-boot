package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysRoleEntity;

import java.util.Map;

/**
 * 角色管理
 *
 * @author : pleier
 * @date : 2018/3/27
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 分页查询
     *
     * @param params 查询参数
     * @return PageUtils
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存
     *
     * @param role 角色
     */
    void save(SysRoleEntity role);

    /**
     * 修改
     *
     * @param role 角色
     */
    void update(SysRoleEntity role);

    /**
     * 批量删除
     *
     * @param roleIds 角色ID数组
     */
    void deleteBatch(Long[] roleIds);
}
