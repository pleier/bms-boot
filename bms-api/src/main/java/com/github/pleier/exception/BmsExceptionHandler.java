package com.github.pleier.exception;

import com.github.pleier.common.exception.BmsException;
import com.github.pleier.common.utils.BmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author : pleier
 * @date : 2018/4/16
 */
@RestControllerAdvice
public class BmsExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BmsException.class)
    public BmsResponse handleRRException(BmsException e){
        BmsResponse r = new BmsResponse();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BmsResponse handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return BmsResponse.error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public BmsResponse handleException(Exception e){
        logger.error(e.getMessage(), e);
        return BmsResponse.error();
    }
}
