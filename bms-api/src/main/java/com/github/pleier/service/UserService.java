package com.github.pleier.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.entity.UserEntity;
import com.github.pleier.form.LoginForm;

import java.util.Map;

/**
 * 用户
 *
 * @author : pleier
 * @date : 2018/4/16
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 根据手机号查询
     *
     * @param mobile
     * @return
     */
    UserEntity queryByMobile(String mobile);

    /**
     * 用户登录
     *
     * @param form 登录表单
     * @return 返回登录信息
     */
    Map<String, Object> login(LoginForm form);
}
