package com.github.pleier.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.dao.SysUserDao;
import com.github.pleier.modules.sys.entity.SysUserEntity;
import com.github.pleier.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author : pleier
 * @date : 2018/3/23
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao,SysUserEntity> implements SysUserService{
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return null;
    }

    @Override
    public void save(SysUserEntity user) {

    }

    @Override
    public void update(SysUserEntity user) {

    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        return false;
    }
}
