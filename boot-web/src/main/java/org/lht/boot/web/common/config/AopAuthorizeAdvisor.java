package org.lht.boot.web.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lht.boot.web.domain.vo.AbstractVO;
import org.springframework.stereotype.Component;

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
    public Object test(ProceedingJoinPoint pjp) throws Throwable {
        Object[] arguments = pjp.getArgs();
        for (Object argument : arguments) {
            if (argument instanceof AbstractVO) {
                AbstractVO abstractVO = (AbstractVO) argument;
                abstractVO.setCreateTime(1111111111111L);
            }
        }
        return pjp.proceed(arguments);
    }
}
