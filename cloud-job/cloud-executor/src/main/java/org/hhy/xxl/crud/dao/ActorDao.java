package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.Actor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * @author LiuHao
 * @date 2020/9/20 18:19
 * @description
 */
@Repository
public class ActorDao extends MysqlCurdDao<Actor, String> {

    @Resource(name = "movieJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * 必须构造函数之后加载，当jbcTemplate被加载
     */
    @Override
    protected void setJdbcTemplate() {
        super.jdbcTemplate = this.jdbcTemplate;
    }
}
