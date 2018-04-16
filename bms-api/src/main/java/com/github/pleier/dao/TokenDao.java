package com.github.pleier.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.github.pleier.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户token
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {
}
