package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : pleier
 * @date : 2018/3/27
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public BmsResponse info(){
        return BmsResponse.ok().put("user", getUser());
    }
}
