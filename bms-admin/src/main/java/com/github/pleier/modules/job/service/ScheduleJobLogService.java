package com.github.pleier.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * @author : pleier
 * @date : 2018/9/17
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {
    /**
     * 查询
     *
     * @param params params
     * @return PageUtils
     */
    PageUtils queryPage(Map<String, Object> params);
}
