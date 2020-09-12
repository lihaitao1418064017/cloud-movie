package org.lht.boot.web.domain.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;
import org.lht.boot.web.common.annotation.AccessLogger;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @description: 日志信息
 * @author: LiHaitao
 * @date: 2020/7/13 11:34
 */
@Data
public class AccessLoggerInfo implements Serializable {

    private String id = RandomUtil.simpleUUID();

    /**
     * 访问的操作
     */
    private String action;

    /**
     * 访问的操作
     *
     * @see AccessLogger#value()
     */
    private String module;

    /**
     * 描述
     *
     * @see AccessLogger#describe() ()
     */
    private String describe;

    /**
     * 访问对应的java方法
     */
    private Method method;

    /**
     * 访问对应的java类
     */
    private Class target;

    /**
     * 请求的参数,参数为java方法的参数而不是http参数,key为参数名,value为参数值.
     */
    private Map<String, Object> parameters;

    /**
     * 请求者ip地址
     */
    private String ip;

    /**
     * 请求的url地址
     */
    private String url;

    /**
     * http 请求头集合
     */
    private Map<String, String> httpHeaders;

    /**
     * http 请求方法, GET,POST...
     */
    private String httpMethod;

    /**
     * 响应结果,方法的返回值
     */
    private Object response;

    /**
     * 请求时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long requestTime;

    /**
     * 响应时间戳
     *
     * @see System#currentTimeMillis()
     */
    private long responseTime;

    /**
     * 异常信息,请求对应方法抛出的异常
     */
    private Throwable exception;

    //    /**
    //     * 当前用户
    //     */
    private String username;

    public String getMethodStr() {
        StringJoiner methodAppender = new StringJoiner(",", method.getName().concat("("), ")");
        String[] parameterNames = parameters.keySet().toArray(new String[parameters.size()]);
        Class[] parameterTypes = method.getParameterTypes();

        for (int i = 0; i < parameterTypes.length; i++) {
            methodAppender.add(parameterTypes[i].getSimpleName().concat(" ").concat(parameterNames.length >= i ? parameterNames[i] : ("arg" + i)));
        }
        return methodAppender.toString();
    }

}
