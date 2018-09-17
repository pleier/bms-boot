package com.github.pleier.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.utils.Query;
import com.github.pleier.modules.job.dao.ScheduleJobDao;
import com.github.pleier.modules.job.entity.ScheduleJobEntity;
import com.github.pleier.modules.job.service.ScheduleJobService;
import com.github.pleier.modules.job.utils.ScheduleUtils;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author : pleier
 * @date : 2018/9/17
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity> implements ScheduleJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 项目启动时初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobEntity> scheduleJobList = this.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");

        Page<ScheduleJobEntity> page = this.selectPage(
                new Query<ScheduleJobEntity>(params).getPage(),
                new EntityWrapper<ScheduleJobEntity>().like(StringUtils.isNotBlank(beanName),"bean_name", beanName)
        );
        return new PageUtils(page);
    }

    @Override
    public void save(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void update(ScheduleJobEntity scheduleJob) {

    }

    @Override
    public void deleteBatch(Long[] jobIds) {

    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        return 0;
    }

    @Override
    public void run(Long[] jobIds) {

    }

    @Override
    public void pause(Long[] jobIds) {

    }

    @Override
    public void resume(Long[] jobIds) {

    }
}
