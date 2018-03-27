package com.github.pleier.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author : pleier
 * @date : 2018/3/27
 */
public interface SysDeptService extends IService<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     * @return List<Long>
     */
    List<Long> queryDetpIdList(Long parentId);

    /**
     * 查询列表
     * @param map map
     * @return List<SysDeptEntity>
     */
    List<SysDeptEntity> queryList(Map<String, Object> map);

    /**
     * 获取子部门ID，用于数据过滤
     * @param deptId 子部门ID
     * @return List<Long>
     */
    List<Long> getSubDeptIdList(Long deptId);

}
