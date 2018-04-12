package com.github.pleier.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 数据字典
 *
 * @author : pleier
 * @date : 2018/4/12
 */

@TableName("sys_dict")
public class SysDictEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 字典名称
     */
    @NotBlank(message="字典名称不能为空")
    private String name;

    /**
     * 字典类型
     */
    @NotBlank(message="字典类型不能为空")
    private String type;

    /**
     * 字典码
     */
    @NotBlank(message="字典码不能为空")
    private String code;

    /**
     * 字典值
     */
    @NotBlank(message="字典值不能为空")
    private String value;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标记  -1：已删除  0：正常
     */
    @TableLogic
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
