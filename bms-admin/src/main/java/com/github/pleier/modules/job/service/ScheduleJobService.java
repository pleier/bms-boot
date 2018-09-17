package com.github.pleier.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author : pleier
 * @date : 2018/9/17
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

    /**
     * 查询
     *
     * @param params params
     * @return PageUtils
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存定时任务
     *
     * @param scheduleJob scheduleJob
     */
    void save(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     * @param scheduleJob scheduleJob
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     *
     * @param jobIds 任务id
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     *
     * @param jobIds 任务id
     * @param status 状态
     * @return int
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     *
     * @param jobIds 任务id
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     *
     * @param jobIds 任务id
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     *
     * @param jobIds 任务id
     */
    void resume(Long[] jobIds);

}
