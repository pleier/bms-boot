package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统日志
 *
 * @author : pleier
 * @date : 2018/4/10
 */
@RequestMapping("/sys/log")
@RestController
public class SysLogController extends AbstractController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:log:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysLogService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

}
