package com.github.pleier.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.entity.UserEntity;
import com.github.pleier.form.RegisterForm;
import com.github.pleier.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : pleier
 * @date : 2018/4/16
 */
@RestController
@RequestMapping("/api")
@Api(tags="注册接口")
public class ApiRegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public BmsResponse register(@RequestBody RegisterForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        UserEntity user = new UserEntity();
        user.setMobile(form.getMobile());
        user.setUsername(form.getMobile());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        user.setCreateTime(new Date());
        userService.insert(user);

        return BmsResponse.ok();
    }
}
