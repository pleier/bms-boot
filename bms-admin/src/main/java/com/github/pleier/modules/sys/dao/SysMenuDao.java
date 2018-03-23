package com.github.pleier.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单
 *
 * @author : pleier
 * @date : 2018/3/23
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

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
     * @return 菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();
}
