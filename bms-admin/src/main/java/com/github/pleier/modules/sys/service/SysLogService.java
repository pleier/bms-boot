package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author : pleier
 * @date: 2017/12/14
 */
public interface SysLogService extends IService<SysLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
