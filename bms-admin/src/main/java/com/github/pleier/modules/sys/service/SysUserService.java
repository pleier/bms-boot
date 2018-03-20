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
 * @date: 2017/11/29
 */
public interface SysUserService extends IService<SysUserEntity>{

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 保存用户
     * @param user
     */
    void save(SysUserEntity user);

    /**
     * 修改用户
     * @param user
     */
    void update(SysUserEntity user);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     * @return
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
