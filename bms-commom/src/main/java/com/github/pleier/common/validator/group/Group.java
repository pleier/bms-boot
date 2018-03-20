package com.github.pleier.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author : pleier
 * @date: 2017/12/12
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
