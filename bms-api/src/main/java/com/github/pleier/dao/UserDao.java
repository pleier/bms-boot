package com.github.pleier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}
