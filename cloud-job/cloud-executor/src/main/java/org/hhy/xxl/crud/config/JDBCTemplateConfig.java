package org.hhy.xxl.crud.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author LiuHao
 * @date 2020/9/13 16:00
 * @description jdbcTemplateConfig的配置类
 */
@Configuration
public class JDBCTemplateConfig {

    @Resource(name = "rbacDataSource")
    private DataSource rbacDataSource;

    @Resource(name = "cloudMovieDataSource")
    private DataSource cloudMovieDatasource;

    @Bean(name = "rbacJdbcTemplate")
    @Qualifier("rbacJdbcTemplate")
    public NamedParameterJdbcTemplate rbacNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(rbacDataSource);
    }

    @Primary
    @Bean(name = "movieJdbcTemplate")
    @Qualifier("movieJdbcTemplate")
    public NamedParameterJdbcTemplate movieNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(cloudMovieDatasource);
    }


}
