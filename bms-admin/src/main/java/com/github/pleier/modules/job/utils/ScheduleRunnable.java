package com.github.pleier.modules.job.utils;

import com.github.pleier.common.utils.SpringContextUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;

/**
 * 执行定时任务
 *
 * @author : pleier
 * @date : 2018/9/17
 */
public class ScheduleRunnable implements Runnable {

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;

        if(StringUtils.isNotBlank(params)){
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        }else{
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {

    }
}
