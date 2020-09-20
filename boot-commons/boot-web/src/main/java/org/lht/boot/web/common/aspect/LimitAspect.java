package org.lht.boot.web.common.aspect;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import jodd.net.HttpStatus;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.lht.boot.cache.redis.RedisUtil;
import org.lht.boot.lang.util.ServletUtil;
import org.lht.boot.web.common.config.LimitProperties;
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
@EnableConfigurationProperties({LimitProperties.class})
public class LimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(LimitAspect.class);

    @Autowired
    private final RedisUtil<String, Integer> redisUtil;

    @Autowired
    private HttpServletResponse response;

    //    private RateLimiter rateLimiter = RateLimiter.create(0.3);

    @Autowired
    private LimitProperties limitProperties;

    /**
     * 构造器注入
     *
     * @param redisUtil
     */
    @Autowired
    public LimitAspect(RedisUtil<String, Integer> redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut("@annotation(org.lht.boot.web.common.annotation.Limit)")
    public void pointcut() {
        // do nothing
    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String key = ServletUtil.getIpAddr(request) + request.getRequestURI();
        Object obj = point.proceed();
        List<String> ipList = limitProperties.getIp();
        if (CollectionUtil.isNotEmpty(ipList)) {
            if (ipList.contains(ServletUtil.getIpAddr(request))) {
                Integer seconds = limitProperties.getPeriod();
                Integer times = limitProperties.getCount();
                Integer maxLimit = redisUtil.get(key);
                if (maxLimit == null) {
                    redisUtil.set(key, times, seconds);
                } else if (maxLimit < times) {
                    redisUtil.set(key, maxLimit + 1, seconds);
                } else {
                    JSONObject responseMessage = new JSONObject();
                    responseMessage.put("code", -1);
                    responseMessage.put("message", "请求太频繁，请稍后再试");
                    output(response, responseMessage.toJSONString());
                }
                return obj;
            }
        }
        JSONObject responseMessage = new JSONObject();
        responseMessage.put("code", -1);
        responseMessage.put("message", "ip校验失败");
        output(response, responseMessage.toJSONString());
        return obj;
    }


    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }


    //    @Around("pointcut()")
    //    public Object around(ProceedingJoinPoint point) throws Throwable {
    //        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    //
    //        MethodSignature signature = (MethodSignature) point.getSignature();
    //        Method method = signature.getMethod();
    //        Limit limitAnnotation = method.getAnnotation(Limit.class);
    //        LimitType limitType = limitAnnotation.limitType();
    //        String name = limitAnnotation.name();
    //        String key;
    //        int limitPeriod = limitAnnotation.period();
    //        int limitCount = limitAnnotation.count();
    //        switch (limitType) {
    //            case IP:
    //                key = ServletUtil.getIpAddr(request);
    //                break;
    //            case CUSTOMER:
    //                key = limitAnnotation.key();
    //                break;
    //            default:
    //                key = StringUtils.upperCase(method.getName());
    //        }
    //        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.prefix() + "_", key + "_" + request.getRequestedSessionId()));
    //        String luaScript = buildLuaScript();
    //        RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
    //        Number count = redisUtil.execute(redisScript, keys, limitCount, limitPeriod);
    //        logger.info("第{}次访问key为 {}，描述为 [{}] 的接口", count, keys, name);
    //        if (count != null && count.intValue() <= limitCount) {
    //            return point.proceed();
    //        } else {
    //            throw new LimitAccessException("接口访问超出频率限制");
    //        }
    //
    //    }

    /**
     * 限流脚本
     * 调用的时候不超过阈值，则直接返回并执行计算器自加。
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";
    }

}
