package org.hhy.cloud.movie.dao;

import org.hhy.cloud.movie.entity.Actor;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description: 演员dao
 *
 * @Author lht
 * @Date 2020/9/12 下午10:31
 **/
@Repository
public interface ActorDao extends BaseMybatisPlusDao<Actor> {

}
