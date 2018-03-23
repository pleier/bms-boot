package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author : pleier
 * @date : 2018/3/23
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     * @return 权限ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户ID
     * @return 菜单ID
     */
    List<Long> queryAllMenuId(Long userId);
}
