package org.lht.boot.cloud.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author lht
 * @date 2020 04/01 8:17
 */

@Aspect
@Component
public class MonitorAspect {

    private static final Logger logger = LoggerFactory.getLogger(MonitorAspect.class);




    @Pointcut("@annotation(org.lht.boot.cloud.admin.controller.Monitor)")
    public void pointcut() {
        // do nothing
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Object proceed = point.proceed();

        return proceed;
    }



}
