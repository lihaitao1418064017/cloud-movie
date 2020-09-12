package org.hhy.cloud.movie.dao;

import org.hhy.cloud.movie.entity.Movie;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * Description: 电影dao
 *
 * @Author lht
 * @Date 2020/9/12 下午10:30
 **/
@Repository
public interface MovieDao extends BaseMybatisPlusDao<Movie> {

}
