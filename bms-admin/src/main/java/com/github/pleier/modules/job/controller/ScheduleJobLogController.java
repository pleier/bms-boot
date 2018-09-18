package com.github.pleier.modules.job.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.job.entity.ScheduleJobLogEntity;
import com.github.pleier.modules.job.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author : pleier
 * @date : 2018/9/18
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    public BmsResponse list(@RequestParam Map<String, Object> params){
        PageUtils page = scheduleJobLogService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 定时任务日志信息
     * @param logId logId
     * @return BmsResponse
     */
    @RequestMapping("/info/{logId}")
    public BmsResponse info(@PathVariable("logId") Long logId){
        ScheduleJobLogEntity log = scheduleJobLogService.selectById(logId);

        return BmsResponse.ok().put("log", log);
    }
}
