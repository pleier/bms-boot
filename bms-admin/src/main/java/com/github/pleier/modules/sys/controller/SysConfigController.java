package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.annotation.SysLog;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.modules.sys.entity.SysConfigEntity;
import com.github.pleier.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统配置信息
 *
 * @author : pleier
 * @date : 2018/4/12
 */
@RequestMapping("/sys/config")
@RestController
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:config:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysConfigService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 配置信息
     *
     * @param id id
     * @return BmsResponse
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public BmsResponse info(@PathVariable("id") Long id) {
        SysConfigEntity config = sysConfigService.selectById(id);

        return BmsResponse.ok().put("config", config);
    }

    /**
     * 保存配置
     *
     * @param config config
     * @return BmsResponse
     */
    @SysLog("保存配置")
    @RequestMapping("/save")
    @RequiresPermissions("sys:config:save")
    public BmsResponse save(@RequestBody SysConfigEntity config) {
        ValidatorUtils.validateEntity(config);

        sysConfigService.save(config);

        return BmsResponse.ok();
    }

    /**
     * 修改配置
     * @param config config
     * @return BmsResponse
     */
    @SysLog("修改配置")
    @RequestMapping("/update")
    @RequiresPermissions("sys:config:update")
    public BmsResponse update(@RequestBody SysConfigEntity config){
        ValidatorUtils.validateEntity(config);

        sysConfigService.update(config);

        return BmsResponse.ok();
    }

    /**
     * 删除配置
     * @param ids ids
     * @return BmsResponse
     */
    @SysLog("删除配置")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public BmsResponse delete(@RequestBody Long[] ids){
        sysConfigService.deleteBatch(ids);

        return BmsResponse.ok();
    }
}
