package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author : pleier
 * @date : 2018/3/23
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return List<Long>
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 保存用户
     *
     * @param user 用户实体
     */
    void save(SysUserEntity user);

    /**
     * 修改
     *
     * @param user 用户实体
     */
    void update(SysUserEntity user);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     * @return boolean
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
