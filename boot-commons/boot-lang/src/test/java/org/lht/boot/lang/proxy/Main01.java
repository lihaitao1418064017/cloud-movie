package org.lht.boot.lang.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/8/17 13:38
 **/
public class Main01 {

    /**
     * 静态代理list
     */
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();

        List<String> proxyList = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader()
                , list.getClass().getInterfaces()
                , (proxy, method, args1) -> method.invoke(list, args1));
        proxyList.add("nihao");
        System.out.println(proxyList);
    }

    /**
     * cglib动态代理，可以实现没有接口的类，使用了动态生成字节码技术，不能对final类继承
     */
    @Test
    public void test02() {

        StudentMethodInterceptorService studentMethodInterceptorService = new StudentMethodInterceptorService();
        StudentMethodInterceptorService proxy = (StudentMethodInterceptorService) Enhancer.create(studentMethodInterceptorService.getClass(),
                new StudentMethodInterceptor());
        proxy.pay();
    }

    /**
     * jdk动态代理,需要有顶层接口才能实现，mybatis的mapper文件，使用了动态生成字节码技术
     */
    @Test
    public void test03() {
        //创建目标对象
        IStudentService target = new StudentServiceImpl();
        //创建代理对象
        IStudentService proxy = (IStudentService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),//代理对象
                new StudentAdvice(target)//做什么事情
        );
        proxy.attendClass();
    }

    /**
     * 当Bean实现接口时，Spring就会用JDK的动态代理
     * 当Bean没有实现接口时，Spring使用CGLib来实现
     * 可以强制使用CGLib（在Spring配置中加入<aop:aspectj-autoproxy proxy-target-class=“true”/>）
     * <p>
     * <p>
     * 使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，在JDK1.6之前比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
     * 在JDK1.6、JDK1.7、JDK1.8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLib代理效率，只有当进行大量调用的时候，JDK1.6和JDK1.7比CGLib代理效率低一点，但是到JDK1.8的时候，JDK代理效率高于CGLib代理
     */


    /**
     * 问题1：如何代理一个final并且没有实现接口的类？
     * 我直接把一个代理类的源代码用字符串拼出来，然后基于这个字符串调用JDK的Compiler（编译期）API，动态的创建一个新的.java文件，然后动态编译这个.java文件，这样也能得到一个新的代理类。
     * 问题2：
     * spring aop是如何利用代理实现的？
     */
    @Test
    public void test04() {

    }

}
