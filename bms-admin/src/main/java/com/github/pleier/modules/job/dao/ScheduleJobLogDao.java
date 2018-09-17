package com.github.pleier.modules.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author : pleier
 * @date : 2018/9/17
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
}
