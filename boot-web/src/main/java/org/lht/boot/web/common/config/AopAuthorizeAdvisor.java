package org.lht.boot.web.common.config;

import cn.hutool.core.date.SystemClock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.lht.boot.web.domain.vo.AbstractVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/27 16:42
 */
@Aspect
@Component
public class AopAuthorizeAdvisor {

    @Pointcut("@annotation(org.springframework.security.access.prepost.PreAuthorize)")
    private void controller() {

    }


    @Around("controller()")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {


        Object[] arguments = pjp.getArgs();
        Method method = null;
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            method = methodSignature.getMethod();
        } else {
            return pjp.proceed();
        }

        for (Object argument : arguments) {
            if (argument instanceof AbstractVO) {
                AbstractVO abstractVO = (AbstractVO) argument;
                Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
                String name = null;
                if (auth.isPresent()) {
                    Authentication authentication = auth.get();
                    name = authentication.getName();
                }
                if (method.getName().equals("add") && abstractVO.getCreateTime() == null && abstractVO.getCreatorUser() == null) {
                    abstractVO.setCreateTime(SystemClock.now());
                    abstractVO.setCreatorUser(name);
                }
                abstractVO.setUpdateUser(name);
                abstractVO.setUpdateTime(SystemClock.now());
            }
        }
        return pjp.proceed(arguments);
    }
}
