package com.github.pleier.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.modules.sys.dao.SysMenuDao;
import com.github.pleier.modules.sys.entity.SysMenuEntity;
import com.github.pleier.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单管理
 *
 * @author : pleier
 * @date : 2018/3/23
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;


    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        return null;
    }

    @Override
    public List<SysMenuEntity> queryListParentId(Long parentId) {
        return null;
    }

    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return null;
    }

    @Override
    public List<SysMenuEntity> getUserMenuList(Long userId) {
        return null;
    }

    @Override
    public void delete(Long menuId) {

    }
}
