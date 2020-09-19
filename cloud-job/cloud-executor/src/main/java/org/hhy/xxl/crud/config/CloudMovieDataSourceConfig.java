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
@ConfigurationProperties(prefix = "spring.datasource.cloud-movie")
@Data
public class CloudMovieDataSourceConfig {

    private String url;
    private String username;
    private String password;

    @Bean(name = "cloudMovieDataSource")
    @Qualifier("cloudMovieDataSource")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
