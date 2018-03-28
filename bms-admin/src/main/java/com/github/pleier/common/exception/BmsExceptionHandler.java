package com.github.pleier.common.exception;

import com.github.pleier.common.utils.BmsResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author : pleier
 * @date : 2018/3/28
 */
@RestControllerAdvice
public class BmsExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BmsException.class)
    public BmsResponse handleRRException(BmsException e) {
        BmsResponse br = new BmsResponse();
        br.put("code", e.getCode());
        br.put("msg", e.getMessage());

        return br;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BmsResponse handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return BmsResponse.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public BmsResponse handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return BmsResponse.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public BmsResponse handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return BmsResponse.error();
    }
}
