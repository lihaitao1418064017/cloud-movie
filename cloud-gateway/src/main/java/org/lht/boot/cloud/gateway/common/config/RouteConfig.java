package org.lht.boot.cloud.gateway.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

/**
 * @author LiHaitao
 * @description RouteConfig:路由配置
 * @date 2020/6/16 16:22
 **/
@Configuration
public class RouteConfig {


    //    @Bean
    //    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    //        String httpUri = "http://httpbin.org:80";
    //        return builder.routes()
    //                .route(p -> p
    //                        .path("/get")
    //                        .filters(f -> f.addRequestHeader("Hello", "World"))
    //                        .uri(httpUri))
    //                .route(p -> p
    //                        .host("*.hystrix.com")
    //                        .filters(f -> f
    //                                .hystrix(config -> config
    //                                        .setName("mycmd")
    //                                        .setFallbackUri("forward:/fallback")))
    //                        .uri(httpUri))
    //                .build();
    //    }


    // tag::fallback[]
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
    // end::fallback[]
}
