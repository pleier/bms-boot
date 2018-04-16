package com.github.pleier.controller;

import com.github.pleier.annotion.Login;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.form.LoginForm;
import com.github.pleier.service.TokenService;
import com.github.pleier.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 登录接口
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录接口")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    @ApiOperation("登录")
    public BmsResponse login(@RequestBody LoginForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        Map<String, Object> map = userService.login(form);

        return BmsResponse.ok(map);
    }

    @Login
    @PostMapping("/logout")
    @ApiOperation("退出")
    public BmsResponse logout(@RequestBody long userId) {
        tokenService.expireToken(userId);
        return BmsResponse.ok();
    }

}
