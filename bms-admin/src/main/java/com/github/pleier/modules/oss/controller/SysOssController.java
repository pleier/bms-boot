package com.github.pleier.modules.oss.controller;

import com.github.pleier.common.exception.BmsException;
import com.github.pleier.common.utils.BmsResponse;
import com.github.pleier.common.utils.ConfigConstant;
import com.github.pleier.common.utils.Constant;
import com.github.pleier.common.utils.PageUtils;
import com.github.pleier.common.validator.ValidatorUtils;
import com.github.pleier.common.validator.group.AliyunGroup;
import com.github.pleier.common.validator.group.QcloudGroup;
import com.github.pleier.common.validator.group.QiniuGroup;
import com.github.pleier.modules.oss.cloud.CloudStorageConfig;
import com.github.pleier.modules.oss.cloud.OSSFactory;
import com.github.pleier.modules.oss.entity.SysOssEntity;
import com.github.pleier.modules.oss.service.SysOssService;
import com.github.pleier.modules.sys.service.SysConfigService;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 *
 * @author : pleier
 * @date : 2018/10/10
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {

    @Autowired
    private SysOssService sysOssService;

    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表查询
     *
     * @param params params
     * @return BmsResponse
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public BmsResponse list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysOssService.queryPage(params);

        return BmsResponse.ok().put("page", page);
    }

    /**
     * 云存储配置信息
     *
     * @return BmsResponse
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public BmsResponse config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return BmsResponse.ok().put("config", config);
    }

    /**
     * 保存云存储配置信息
     *
     * @param config config
     * @return BmsResponse
     */
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public BmsResponse saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

        return BmsResponse.ok();
    }

    /**
     * 上传文件
     *
     * @param file file
     * @return BmsResponse
     * @throws Exception BmsException
     */
    @RequestMapping("/upload")
    @RequiresPermissions("sys:oss:all")
    public BmsResponse upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BmsException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.insert(ossEntity);

        return BmsResponse.ok().put("url", url);
    }

    /**
     * 删除（删除本地信息，云端未删除文件）
     *
     * @param ids ids
     * @return BmsResponse
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public BmsResponse delete(@RequestBody Long[] ids) {
        sysOssService.deleteBatchIds(Arrays.asList(ids));

        return BmsResponse.ok();
    }
}
