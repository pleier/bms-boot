package com.github.pleier;

import com.github.pleier.common.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : pleier
 * @date : 2018/3/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void testRedis(){
        redisUtils.set("hello","ssaassa");
    }
}
