package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.annotation.SysLog;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.modules.sys.entity.SysRoleEntity;
import com.github.pleier.modules.sys.service.SysRoleDeptService;
import com.github.pleier.modules.sys.service.SysRoleMenuService;
import com.github.pleier.modules.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author : pleier
 * @date : 2018/4/12
 */
@RequestMapping("/sys/role")
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 角色列表
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysRoleService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 角色列表
     *
     * @return BmsResponse
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public BmsResponse select() {
        List<SysRoleEntity> list = sysRoleService.selectList(null);

        return BmsResponse.ok().put("list", list);
    }

    /**
     * 角色信息
     *
     * @param roleId roleId
     * @return BmsResponse
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public BmsResponse info(@PathVariable("roleId") Long roleId) {
        SysRoleEntity role = sysRoleService.selectById(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        //查询角色对应的部门
        List<Long> deptIdList = sysRoleDeptService.queryDeptIdList(new Long[]{roleId});
        role.setDeptIdList(deptIdList);

        return BmsResponse.ok().put("role", role);
    }

    /**
     * 保存角色
     *
     * @param role role
     * @return BmsResponse
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public BmsResponse save(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);

        sysRoleService.save(role);

        return BmsResponse.ok();
    }

    /**
     * 修改角色
     *
     * @param role role
     * @return BmsResponse
     */
    @SysLog("修改角色")
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public BmsResponse update(@RequestBody SysRoleEntity role) {
        ValidatorUtils.validateEntity(role);

        sysRoleService.update(role);

        return BmsResponse.ok();
    }

    /**
     * 删除角色
     *
     * @param roleIds roleIds
     * @return BmsResponse
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public BmsResponse delete(@RequestBody Long[] roleIds) {
        sysRoleService.deleteBatch(roleIds);

        return BmsResponse.ok();
    }
}
