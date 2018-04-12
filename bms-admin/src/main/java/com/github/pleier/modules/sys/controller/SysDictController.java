package com.github.pleier.modules.sys.controller;

import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.modules.sys.entity.SysDictEntity;
import com.github.pleier.modules.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 数据字典
 *
 * @author : pleier
 * @date : 2018/4/10
 */
@RequestMapping("/sys/dict")
@RestController
public class SysDictController extends AbstractController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDictService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public BmsResponse info(@PathVariable("id") Long id) {
        SysDictEntity dict = sysDictService.selectById(id);

        return BmsResponse.ok().put("dict", dict);
    }

    /**
     * 保存
     *
     * @param dict dict
     * @return SysDictEntity
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public BmsResponse save(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.insert(dict);

        return BmsResponse.ok();
    }

    /**
     * 修改
     *
     * @param dict dict
     * @return BmsResponse
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public BmsResponse update(@RequestBody SysDictEntity dict) {
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.updateById(dict);

        return BmsResponse.ok();
    }

    /**
     * 删除
     *
     * @param ids ids
     * @return BmsResponse
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public BmsResponse delete(@RequestBody Long[] ids) {
        sysDictService.deleteBatchIds(Arrays.asList(ids));

        return BmsResponse.ok();
    }


}
