package com.github.pleier.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pleier.entity.TokenEntity;

/**
 * 用户token
 *
 * @author : pleier
 * @date : 2018/4/16
 */
public interface TokenService extends IService<TokenEntity> {

    /**
     * 查询
     *
     * @param token
     * @return
     */
    TokenEntity queryByToken(String token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return 返回token信息
     */
    TokenEntity createToken(long userId);

    /**
     * 设置token过期
     *
     * @param userId 用户ID
     */
    void expireToken(long userId);
}
