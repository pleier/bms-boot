package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.modules.sys.entity.SysMenuEntity;
import com.github.pleier.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统菜单
 *
 * @author : pleier
 * @date : 2018/3/27
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 导航菜单
     *
     * @return
     */
    @RequestMapping("/nav")
    public BmsResponse nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        return BmsResponse.ok().put("menuList", menuList);
    }
}
