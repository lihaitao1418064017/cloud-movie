package org.hhy.xxl.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 *
 * @author LiuHao
 * @date 2020/9/21 16:09
 * @description 事务管理器配置,事务在Service层处理
*/
@Configuration
public class TransactionManagerConfig {

    @Resource(name = "rbacDataSource")
    private DataSource rbacDataSource;

    @Resource(name = "cloudMovieDataSource")
    private DataSource cloudMovieDatasource;

    @Bean(name = "rabcTxManager")
    public PlatformTransactionManager rbacTxManager(){
        return new DataSourceTransactionManager(rbacDataSource);
    }

    @Bean(name = "cloudMovieTxManager")
    public PlatformTransactionManager cloudMovieTxManager(){
        return new DataSourceTransactionManager(cloudMovieDatasource);
    }
}
