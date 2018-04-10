package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.annotation.SysLog;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.Assert;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.common.validator.group.AddGroup;
import com.github.pleier.common.validator.group.UpdateGroup;
import com.github.pleier.modules.sys.entity.SysUserEntity;
import com.github.pleier.modules.sys.service.SysUserRoleService;
import com.github.pleier.modules.sys.service.SysUserService;
import com.github.pleier.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author : pleier
 * @date : 2018/3/27
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 所有用户列表
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysUserService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     *
     * @return BmsResponse
     */
    @RequestMapping("/info")
    public BmsResponse info() {
        return BmsResponse.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     *
     * @param password    password
     * @param newPassword newPassword
     * @return BmsResponse
     */
    @SysLog("修改密码")
    @RequestMapping("/password")
    public BmsResponse password(String password, String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");

        //原密码
        password = ShiroUtils.sha256(password, getUser().getSalt());
        //新密码
        newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return BmsResponse.error("原密码不正确");
        }

        return BmsResponse.ok();
    }

    /**
     * 用户信息
     *
     * @param userId userId
     * @return BmsResponse
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public BmsResponse info(@PathVariable("userId") Long userId) {
        SysUserEntity user = sysUserService.selectById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return BmsResponse.ok().put("user", user);
    }

    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    public BmsResponse save(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, AddGroup.class);

        sysUserService.save(user);

        return BmsResponse.ok();
    }

    /**
     * 修改用户
     *
     * @param user user
     * @return BmsResponse
     */
    @SysLog("修改用户")
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public BmsResponse update(@RequestBody SysUserEntity user) {
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        sysUserService.update(user);

        return BmsResponse.ok();
    }

    /**
     * 删除用户
     *
     * @param userIds userIds
     * @return BmsResponse
     */
    @SysLog("删除用户")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public BmsResponse delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return BmsResponse.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return BmsResponse.error("当前用户不能删除");
        }

        sysUserService.deleteBatchIds(Arrays.asList(userIds));

        return BmsResponse.ok();
    }
}
