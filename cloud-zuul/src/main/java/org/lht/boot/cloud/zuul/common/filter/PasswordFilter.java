package org.lht.boot.cloud.zuul.common.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiHaitao
 * @description PasswordFilter:密码过滤器
 * @date 2020/6/12 13:26
 **/
@Component
public class PasswordFilter extends ZuulFilter {

    private static final int PASSWORD_ORDER = 0;

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "post"; // 请求处理完成后执行的filter
    }

    /**
     * filterOrder：过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return PASSWORD_ORDER; // 优先级为0，数字越大，优先级越低
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //ctx:可以获取请求服务，headers，代理Proxy等相关信息
        if (filterOrder() > 0) {
            // 判断上一个过滤器结果为true，否则就不走下面过滤器，直接跳过后面的所有过滤器并返回 上一个过滤器不通过的结果。
            RequestContext ctx = RequestContext.getCurrentContext();
            return (boolean) ctx.get("isSuccess");
        }
        return true;
    }

    /**
     * run：过滤器的具体逻辑。。
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // TODO: 2020/6/12 密码校验必须是权限系统并且是登录请求 
        String username = request.getParameter("password");
        request.getContextPath();
        if (null != username && username.equals("123456")) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);
            return null;
        } else {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(400);
            ctx.setResponseBody("The password cannot be empty");
            ctx.set("isSuccess", false);
            return null;
        }
    }

}
