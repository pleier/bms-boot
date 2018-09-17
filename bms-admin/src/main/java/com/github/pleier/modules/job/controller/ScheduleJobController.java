package com.github.pleier.modules.job.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务
 *
 * @author : pleier
 * @date : 2018/9/17
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 定时任务列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public BmsResponse list(@RequestParam Map<String, Object> params){
        PageUtils page = scheduleJobService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }
}
