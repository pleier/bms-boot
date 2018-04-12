package com.github.pleier.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.utils.Query;
import com.github.pleier.modules.sys.dao.SysDictDao;
import com.github.pleier.modules.sys.entity.SysDictEntity;
import com.github.pleier.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 数据字典
 *
 * @author : pleier
 * @date : 2018/4/12
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");

        Page<SysDictEntity> page = this.selectPage(
                new Query<SysDictEntity>(params).getPage(),
                new EntityWrapper<SysDictEntity>()
                        .like(StringUtils.isNotBlank(name), "name", name)
        );

        return new PageUtils(page);
    }
}
