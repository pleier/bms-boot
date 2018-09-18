package com.github.pleier.modules.job.controller;

import com.github.pleier.common.annotation.SysLog;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.modules.job.entity.ScheduleJobEntity;
import com.github.pleier.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param params 查询参数
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = scheduleJobService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 定时任务信息
     *
     * @param jobId 任务Id
     * @return BmsResponse
     */
    @RequestMapping("/info/{jobId}")
    @RequiresPermissions("sys:schedule:info")
    public BmsResponse info(@PathVariable("jobId") Long jobId) {
        ScheduleJobEntity schedule = scheduleJobService.selectById(jobId);

        return BmsResponse.ok().put("schedule", schedule);
    }

    /**
     * 保存
     *
     * @param scheduleJob scheduleJob
     * @return BmsResponse
     */
    @SysLog("保存定时任务")
    @RequestMapping("/save")
    @RequiresPermissions("sys:schedule:save")
    public BmsResponse save(ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.save(scheduleJob);

        return BmsResponse.ok();
    }

    /**
     * 修改定时任务
     *
     * @param scheduleJob scheduleJob
     * @return BmsResponse
     */
    @SysLog("修改定时任务")
    @RequestMapping("/update")
    @RequiresPermissions("sys:schedule:update")
    public BmsResponse update(@RequestBody ScheduleJobEntity scheduleJob) {
        ValidatorUtils.validateEntity(scheduleJob);

        scheduleJobService.update(scheduleJob);

        return BmsResponse.ok();
    }

    /**
     * 删除定时任务
     *
     * @param jobIds jobIds
     * @return BmsResponse
     */
    @SysLog("删除定时任务")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:schedule:delete")
    public BmsResponse delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);

        return BmsResponse.ok();
    }

    /**
     * 立即执行任务
     *
     * @param jobIds jobIds
     * @return BmsResponse
     */
    @SysLog("立即执行任务")
    @RequestMapping("/run")
    @RequiresPermissions("sys:schedule:run")
    public BmsResponse run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);

        return BmsResponse.ok();
    }

    /**
     * 暂停定时任务
     *
     * @param jobIds jobIds
     * @return BmsResponse
     */
    @SysLog("暂停定时任务")
    @RequestMapping("/pause")
    @RequiresPermissions("sys:schedule:pause")
    public BmsResponse pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);

        return BmsResponse.ok();
    }

    /**
     * 恢复定时任务
     *
     * @param jobIds jobIds
     * @return BmsResponse
     */
    @SysLog("恢复定时任务")
    @RequestMapping("/resume")
    @RequiresPermissions("sys:schedule:resume")
    public BmsResponse resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);

        return BmsResponse.ok();
    }
}
