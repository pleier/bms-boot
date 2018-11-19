package com.github.pleier.modules.oss.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author : pleier
 * @date : 2018/10/10
 */
public interface SysOssService extends IService<SysOssEntity> {
    /**
     * 分页查询数据
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
