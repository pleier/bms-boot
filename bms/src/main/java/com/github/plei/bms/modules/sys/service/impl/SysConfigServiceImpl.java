package com.github.plei.bms.modules.sys.service.impl;

import com.github.plei.bms.modules.sys.dao.SysConfigDao;
import com.github.plei.bms.modules.sys.entity.SysConfigEntity;
import com.github.plei.bms.modules.sys.redis.SysConfigRedis;
import com.github.plei.bms.modules.sys.service.SysConfigService;
import com.github.plei.common.exception.BmsException;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author : pleier
 * @date: 2017/12/15
 */
@Service("sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    @Qualifier("sysConfigDao")
    private SysConfigDao sysConfigDao;

    @Autowired
    @Qualifier("sysConfigRedis")
    private SysConfigRedis sysConfigRedis;

    @Override
    public void save(SysConfigEntity config) {
        sysConfigDao.save(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    public void update(SysConfigEntity config) {
        sysConfigDao.update(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    public void updateValueByKey(String key, String value) {
        sysConfigDao.updateValueByKey(key, value);
        sysConfigRedis.delete(key);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        for (Long id : ids) {
            SysConfigEntity config = queryObject(id);
            sysConfigRedis.delete(config.getKey());
        }
        sysConfigDao.deleteBatch(ids);
    }

    @Override
    public List<SysConfigEntity> queryList(Map<String, Object> map) {
        return sysConfigDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysConfigDao.queryTotal(map);
    }

    @Override
    public SysConfigEntity queryObject(Long id) {
        return sysConfigDao.queryObject(id);
    }

    @Override
    public String getValue(String key) {
        SysConfigEntity config = sysConfigRedis.get(key);
        if(config == null){
            config = sysConfigDao.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BmsException("获取参数失败");
        }
    }
}
