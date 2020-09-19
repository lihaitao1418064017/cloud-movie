package org.hhy.xxl.crud.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 23:21
 * @description rbac相关数据源配置类
*/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.rbac")
@Data
public class RbacDataSourceConfig {

    private String url;
    private String username;
    private String password;

    @Bean(name = "rbacDataSource")
    @Qualifier("rbacDataSource")
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
