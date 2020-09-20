package org.hhy.xxl.crud.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 23:52
 * @description cloud-movie相关数据库配置
*/
@Configuration
@Data
public class CloudMovieDataSourceConfig {

    @Bean(name = "cloudMovieDataSource")
    @Qualifier("cloudMovieDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cloud-movie")
    public DataSource dataSource(){
        return new DruidDataSource();
    }
}
