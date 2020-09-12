package org.hhy.cloud.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.lht.boot.web", "org.hhy.cloud.movie"})
@MapperScan(basePackages = {"org.hhy.cloud.movie.dao"})
public class CloudMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMovieApplication.class, args);
    }

}
