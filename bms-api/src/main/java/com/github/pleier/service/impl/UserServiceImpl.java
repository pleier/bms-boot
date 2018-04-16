package com.github.pleier.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.common.exception.BmsException;
import com.github.pleier.common.validator.Assert;
import com.github.pleier.dao.UserDao;
import com.github.pleier.entity.TokenEntity;
import com.github.pleier.entity.UserEntity;
import com.github.pleier.form.LoginForm;
import com.github.pleier.service.TokenService;
import com.github.pleier.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private TokenService tokenService;

    @Override
    public UserEntity queryByMobile(String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        return baseMapper.selectOne(userEntity);
    }

    @Override
    public Map<String, Object> login(LoginForm form) {
        UserEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new BmsException("手机号或密码错误");
        }

        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

        return map;
    }
}
