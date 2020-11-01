package org.hhy.cloud.crawl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @Classname org.hhy.cloud.crawl.CrawlApplication
 * @Description  启动类
 * @Date 2020/9/13 6:52 PM
 * @Created by yupeng
 */
//@EnableKafka
@SpringBootApplication(scanBasePackages = {"org.lht.boot.web", "org.lht.boot.mq", "org.hhy.cloud"})
@MapperScan(basePackages = {"org.hhy.cloud.crawl.dao"})
public class CrawlApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrawlApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(CrawlApplication.class);
    }

}
