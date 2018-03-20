package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.modules.sys.entity.SysDictEntity;

import java.util.Map;

/**
 * 数据字典
 *
 * @author : pleier
 * @date : 2018/3/20
 */
public interface SysDictService extends IService<SysDictEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
