package com.github.pleier.modules.oss.cloud;

import com.github.pleier.common.utils.ConfigConstant;
import com.github.pleier.common.utils.Constant;
import com.github.pleier.common.utils.SpringContextUtils;
import com.github.pleier.modules.sys.service.SysConfigService;

/**
 * 文件上传factory
 *
 * @author : pleier
 * @date : 2018/10/10
 */
public class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }

        return null;
    }
}
