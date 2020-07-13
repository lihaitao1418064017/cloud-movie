package org.lht.boot.web.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.lht.boot.lang.util.AopUtil;
import org.lht.boot.lang.util.IpUtils;
import org.lht.boot.lang.util.WebUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.common.event.AccessLoggerEvent;
import org.lht.boot.web.domain.entity.AccessLoggerInfo;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


/**
 * @description: 使用AOP记录访问日志, 并触发{@link org.lht.boot.web.common.event.AccessLoggerEvent}
 * @author: LiHaitao
 * @date: 2020/7/13 13:08
 */
@Slf4j
public class AopAccessLoggerSupport extends StaticMethodMatcherPointcutAdvisor implements ApplicationEventPublisherAware {

    public static final ParameterNameDiscoverer NAME_DISCOVERER = new LocalVariableTableParameterNameDiscoverer();

    private ApplicationEventPublisher eventPublisher;

    public AopAccessLoggerSupport() {
        setAdvice((MethodInterceptor) (MethodInvocation methodInvocation) -> {
            AccessLoggerInfo info = createLogger(methodInvocation);
            Object response;
            try {
                response = methodInvocation.proceed();
                info.setResponse(response);
                info.setResponseTime(System.currentTimeMillis());
                //获取当前用户
                Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
                if (auth.isPresent()) {
                    Authentication authentication = auth.get();
                    String name = authentication.getName();
                    info.setUsername(name);
                }
            } catch (Throwable e) {
                info.setException(e);
                throw e;
            } finally {
                eventPublisher.publishEvent(new AccessLoggerEvent(info));
            }
            return response;
        });
    }

    protected AccessLoggerInfo createLogger(MethodInvocation methodInvocation) {
        AccessLoggerInfo info = new AccessLoggerInfo();
        info.setRequestTime(System.currentTimeMillis());

        AccessLogger classAnn = AopUtil.findAnnotation(methodInvocation.getThis().getClass(), AccessLogger.class);
        AccessLogger methodAnn = AopUtil.findAnnotation(methodInvocation.getThis().getClass(), methodInvocation.getMethod(), AccessLogger.class);

        String describe = Stream.of(classAnn, methodAnn)
                .filter(Objects::nonNull)
                .map(AccessLogger::describe)
                .flatMap(Stream::of)
                .reduce((c, s) -> c.concat("\n").concat(s))
                .orElse("");

        info.setAction(methodAnn == null ? methodInvocation.getMethod().getName() : methodAnn.value());
        info.setModule(classAnn == null ? methodInvocation.getClass().getSimpleName() : classAnn.value());
        info.setDescribe(describe);


        info.setParameters(this.arguments(methodInvocation));
        info.setTarget(methodInvocation.getClass());
        info.setMethod(methodInvocation.getMethod());

        HttpServletRequest request = WebUtil.getHttpServletRequest();
        if (null != request) {
            info.setHttpHeaders(WebUtil.getHeaders(request));
            info.setIp(IpUtils.getIpAddr(request));
            info.setHttpMethod(request.getMethod());
            info.setUrl(request.getRequestURL().toString());
        }

        return info;
    }

    private Map<String, Object> arguments(MethodInvocation methodInvocation) {

        String[] argNames = NAME_DISCOVERER.getParameterNames(methodInvocation.getMethod());

        Object[] args = methodInvocation.getArguments();
        Map<String, Object> argMap = new LinkedHashMap();
        int i = 0;

        for (int len = args.length; i < len; ++i) {
            argMap.put(argNames != null && argNames[i] != null ? argNames[i] : "arg" + i, args[i]);
        }
        return argMap;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean matches(Method method, Class<?> aClass) {
        AccessLogger ann = AopUtil.findAnnotation(aClass, method, AccessLogger.class);
        //注解了并且未取消
        return null != ann && !ann.ignore();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
