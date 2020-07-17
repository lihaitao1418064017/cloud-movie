package org.lht.boot.cloud.gateway.common.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @description: 过滤器
 * @author: LiHaitao
 * @date: 2020/6/22 16:28
 */
@Component
@Slf4j
public class LoginGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***********come in LoginGateWayFilter:  " + new Date());
        // 不是登录请求，直接向下执行
        if (!StrUtil.containsAnyIgnoreCase(exchange.getRequest().getURI().getPath()
                , "/login")) {
            return chain.filter(exchange);
        }
        String uname = exchange.getRequest().getQueryParams().getFirst("username");

        if (uname == null) {
            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
