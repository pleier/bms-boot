package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.annotation.SysLog;
import com.github.pleier.common.exception.BmsException;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.Constant;
import com.github.pleier.modules.sys.entity.SysMenuEntity;
import com.github.pleier.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @return BmsResponse
     */
    @RequestMapping("/nav")
    public BmsResponse nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        return BmsResponse.ok().put("menuList", menuList);
    }

    /**
     * 所有菜单列表
     *
     * @return List<SysMenuEntity>
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuEntity> list() {
        List<SysMenuEntity> menuList = sysMenuService.selectList(null);
        for (SysMenuEntity sysMenuEntity : menuList) {
            SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     *
     * @return BmsResponse
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public BmsResponse select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return BmsResponse.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     *
     * @param menuId menuId
     * @return BmsResponse
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public BmsResponse info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.selectById(menuId);
        return BmsResponse.ok().put("menu", menu);
    }

    /**
     * 保存
     *
     * @param menu menu
     * @return BmsResponse
     */
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public BmsResponse save(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.insert(menu);

        return BmsResponse.ok();
    }

    /**
     * 修改
     *
     * @param menu menu
     * @return BmsResponse
     */
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public BmsResponse update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return BmsResponse.ok();
    }

    /**
     * 删除
     *
     * @param menuId menuId
     * @return BmsResponse
     */
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public BmsResponse delete(long menuId) {
        if (menuId <= 31) {
            return BmsResponse.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return BmsResponse.error("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return BmsResponse.ok();
    }

    /**
     * 验证参数是否正确
     *
     * @param menu menu
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new BmsException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new BmsException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new BmsException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == Constant.MenuType.CATALOG.getValue() ||
                menu.getType() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new BmsException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new BmsException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
