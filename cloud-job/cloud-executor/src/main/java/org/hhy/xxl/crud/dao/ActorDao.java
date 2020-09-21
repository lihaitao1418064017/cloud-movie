package org.hhy.xxl.crud.dao;

import org.hhy.xxl.job.executor.bean.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 *
 * @author LiuHao
 * @date 2020/9/20 18:19
 * @description
*/
@Repository
public class ActorDao extends MysqlCurdDao<Actor,String> {


    public ActorDao() {
        super.setJdbcTemplate(jdbcTemplate);
    }
    /**TODO 解决bean注入问题，初始化时不执行@Resource*/
    @Resource(name = "movieJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

}
