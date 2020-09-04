package org.lht.boot.lang.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: wangyu
 * @Date: Created in 2020-03-05 9:54
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {
        SkipSslVerificationHttpRequestFactory factory = new SkipSslVerificationHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(15000);
        RestTemplate restTemplate = new RestTemplate(factory);
        //设置字符编码
       /* StringHttpMessageConverter t = new StringHttpMessageConverter();
        //设置为false就可以修改header中的accept-charset属性
        t.setWriteAcceptCharset(false);
        t.setDefaultCharset(StandardCharsets.UTF_8);
        restTemplate.getMessageConverters().add(0, t);*/
        return restTemplate;
    }


}
