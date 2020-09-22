package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.Actor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author LiuHao
 * @date 2020/9/20 18:19
 * @description
 */
@Repository
public class ActorDao extends MysqlCurdDao<Actor, String> {

    //    @Resource(name = "movieJdbcTemplate")
    //    private NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    protected void setJdbcTemplate(@Qualifier("movieJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate) {
        super.jdbcTemplate = jdbcTemplate;
    }
}
