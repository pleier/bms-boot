package com.github.pleier.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 系统配置
 *
 * @author : pleier
 * @date : 2018/3/28
 */
@TableName("sys_config")
public class SysConfigEntity implements Serializable {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 参数名
     */
    @NotBlank(message = "参数名不能为空")
    private String key;

    /**
     * 参数值
     */
    @NotBlank(message = "参数值不能为空")
    private String value;

    /**
     * 备注
     */
    private String remark;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
