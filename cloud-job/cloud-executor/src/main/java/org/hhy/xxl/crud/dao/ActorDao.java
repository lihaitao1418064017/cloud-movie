package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.Actor;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/**
 *
 * @author LiuHao
 * @date 2020/9/20 18:19
 * @description
*/
public class ActorDao extends MysqlCurdDao<Actor,String> {

    @Override
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate){
        super.setJdbcTemplate(jdbcTemplate);
    }
}
