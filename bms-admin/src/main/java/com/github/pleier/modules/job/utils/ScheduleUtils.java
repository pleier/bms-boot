package com.github.pleier.modules.job.utils;

import com.github.pleier.common.exception.BmsException;
import com.github.pleier.common.utils.Constant;
import com.github.pleier.modules.job.entity.ScheduleJobEntity;
import org.quartz.*;

/**
 * 定时任务工具类
 *
 * @author : pleier
 * @date : 2018/9/17
 */
public class ScheduleUtils {
    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     *
     * @param jobId 任务id
     * @return TriggerKey
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     *
     * @param jobId jobId
     * @return JobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     *
     * @param scheduler scheduler
     * @param jobId     jobId
     * @return CronTrigger
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new BmsException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建定时任务
     *
     * @param scheduler   scheduler
     * @param scheduleJob scheduleJob
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJobEntity.JOB_PARAM_KEY, scheduleJob);

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            if (scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()) {
                pauseJob(scheduler, scheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            throw new BmsException("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务
     *
     * @param scheduler   scheduler
     * @param scheduleJob scheduleJob
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put(ScheduleJobEntity.JOB_PARAM_KEY, scheduleJob);

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            if (scheduleJob.getStatus() == Constant.ScheduleStatus.PAUSE.getValue()) {
                pauseJob(scheduler, scheduleJob.getJobId());
            }

        } catch (SchedulerException e) {
            throw new BmsException("更新定时任务失败", e);
        }
    }

    /**
     * 立即执行任务
     *
     * @param scheduler   scheduler
     * @param scheduleJob scheduleJob
     */
    public static void run(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(ScheduleJobEntity.JOB_PARAM_KEY, scheduleJob);

            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
            throw new BmsException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     *
     * @param scheduler scheduler
     * @param jobId     jobId
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new BmsException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     *
     * @param scheduler scheduler
     * @param jobId     jobId
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new BmsException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param scheduler scheduler
     * @param jobId     jobId
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new BmsException("删除定时任务失败", e);
        }
    }
}