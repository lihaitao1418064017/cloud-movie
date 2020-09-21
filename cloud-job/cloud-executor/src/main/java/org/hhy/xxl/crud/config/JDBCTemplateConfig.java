package org.hhy.xxl.crud.config;

import org.hhy.xxl.crud.dao.ActorDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
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
    public NamedParameterJdbcTemplate rbacNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(rbacDataSource);
    }

    @Primary
    @Bean(name = "movieJdbcTemplate")
    @Qualifier("movieJdbcTemplate")
    public NamedParameterJdbcTemplate movieNamedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(cloudMovieDatasource);
    }

    @Bean
    public ActorDao actorDao(){
        ActorDao actorDao =  new ActorDao();
        //注入相应的数据源NamedParameterJdbcTemplate
        actorDao.setJdbcTemplate(movieNamedParameterJdbcTemplate());
        return actorDao;
    }
}
