package com.github.pleier.controller;

import com.github.pleier.annotion.Login;
import com.github.pleier.annotion.LoginUser;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:47
 */
@RestController
@RequestMapping("/api")
@Api(tags="测试接口")
public class ApiTestController {

    @Login
    @GetMapping("/userInfo")
    @ApiOperation(value="获取用户信息", response=UserEntity.class)
    public BmsResponse userInfo(@ApiIgnore @LoginUser UserEntity user){
        return BmsResponse.ok().put("user", user);
    }

    @Login
    @GetMapping("/userId")
    @ApiOperation("获取用户ID")
    public BmsResponse userInfo(@ApiIgnore @RequestAttribute("userId") Integer userId){
        return BmsResponse.ok().put("userId", userId);
    }

    @GetMapping("/notToken")
    @ApiOperation("忽略Token验证测试")
    public BmsResponse notToken(){
        return BmsResponse.ok().put("msg", "无需token也能访问。。。");
    }

}
