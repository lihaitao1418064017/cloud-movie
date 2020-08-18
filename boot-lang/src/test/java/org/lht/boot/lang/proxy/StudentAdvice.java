package org.lht.boot.lang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StudentAdvice implements InvocationHandler {


    private IStudentService target;

    public StudentAdvice(IStudentService target) {
        this.target = target;
    }

    /**
     * 代理方法, 每次调用目标方法时都会进到这里
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(target, args);
    }

    /**
     * 前置增强
     */
    private void before() {
        System.out.println("对学生身份进行验证.");
    }
}