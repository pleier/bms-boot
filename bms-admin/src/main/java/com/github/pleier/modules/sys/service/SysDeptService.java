package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author : pleier
 * @date: 2017/12/11
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 查询部门列表
     *
     * @param map
     * @return
     */
    List<SysDeptEntity> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);

    /**
     * 获取子部门ID，用于数据过滤
     */
    List<Long> getSubDeptIdList(Long deptId);
}
