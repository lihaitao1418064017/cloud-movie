package org.lht.boot.lang.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/17 14:04
 **/
public class StudentMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        return methodProxy.invokeSuper(o, args);
    }


    /**
     * 前置增强
     */
    private void before() {
        System.out.println("对学生身份进行验证.");
    }
}
