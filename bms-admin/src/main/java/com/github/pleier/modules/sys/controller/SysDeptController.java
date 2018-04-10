package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.Constant;
import com.github.pleier.modules.sys.entity.SysDeptEntity;
import com.github.pleier.modules.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 * @author : pleier
 * @date : 2018/4/10
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     *
     * @return List<SysDeptEntity>
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public List<SysDeptEntity> list() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<>(16));

        return deptList;
    }

    /**
     * 选择部门（添加、修改菜单）
     *
     * @return BmsResponse
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public BmsResponse select() {
        List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<>(16));

        //添加一级部门
        if (getUserId() == Constant.SUPER_ADMIN) {
            SysDeptEntity root = new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }

        return BmsResponse.ok().put("deptList", deptList);
    }

    /**
     * 上级部门id（管理员为0）
     *
     * @return BmsResponse
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public BmsResponse info() {
        long deptId = 0;
        if (getUserId() != Constant.SUPER_ADMIN) {
            List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<>(16));
            Long parentId = null;
            for (SysDeptEntity sysDeptEntity : deptList) {
                if (parentId == null) {
                    parentId = sysDeptEntity.getParentId();
                    continue;
                }

                if (parentId > sysDeptEntity.getParentId().longValue()) {
                    parentId = sysDeptEntity.getParentId();
                }
            }
            deptId = parentId;
        }

        return BmsResponse.ok().put("deptId", deptId);
    }

    /**
     * 部门信息
     *
     * @param deptId deptId
     * @return BmsResponse
     */
    @RequestMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public BmsResponse info(@PathVariable("deptId") Long deptId) {
        SysDeptEntity dept = sysDeptService.selectById(deptId);

        return BmsResponse.ok().put("dept", dept);
    }

    /**
     * 保存
     *
     * @param dept dept
     * @return BmsResponse
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dept:save")
    public BmsResponse save(@RequestBody SysDeptEntity dept) {
        sysDeptService.insert(dept);

        return BmsResponse.ok();
    }

    /**
     * 修改
     *
     * @param dept dept
     * @return BmsResponse
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public BmsResponse update(@RequestBody SysDeptEntity dept) {
        sysDeptService.updateById(dept);

        return BmsResponse.ok();
    }

    /**
     * 删除
     *
     * @param deptId deptId
     * @return BmsResponse
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public BmsResponse delete(long deptId) {
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return BmsResponse.error("请先删除子部门");
        }

        sysDeptService.deleteById(deptId);

        return BmsResponse.ok();
    }
}
