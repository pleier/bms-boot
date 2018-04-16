package com.github.pleier.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pleier.dao.TokenDao;
import com.github.pleier.entity.TokenEntity;
import com.github.pleier.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 用户token
 * @author : pleier
 * @date : 2018/4/16
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao,TokenEntity> implements TokenService {
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;

    @Override
    public TokenEntity queryByToken(String token) {
        return this.selectOne(new EntityWrapper<TokenEntity>().eq("token", token));
    }

    @Override
    public TokenEntity createToken(long userId) {
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //生成token
        String token = generateToken();

        //保存或更新用户token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);
        this.insertOrUpdate(tokenEntity);

        return tokenEntity;
    }

    @Override
    public void expireToken(long userId){
        Date now = new Date();

        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(now);
        this.insertOrUpdate(tokenEntity);
    }

    private String generateToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
