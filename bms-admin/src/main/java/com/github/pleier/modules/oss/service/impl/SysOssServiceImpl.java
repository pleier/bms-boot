package com.github.pleier.modules.oss.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.utils.Query;
import com.github.pleier.modules.oss.dao.SysOssDao;
import com.github.pleier.modules.oss.entity.SysOssEntity;
import com.github.pleier.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文件上传
 *
 * @author : pleier
 * @date : 2018/10/10
 */
@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysOssEntity> page = this.selectPage(
                new Query<SysOssEntity>(params).getPage()
        );

        return new PageUtils(page);
    }
}
