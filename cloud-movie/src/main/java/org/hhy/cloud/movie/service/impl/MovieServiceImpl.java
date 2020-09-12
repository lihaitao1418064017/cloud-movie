package org.hhy.cloud.movie.service.impl;

import org.hhy.cloud.movie.dao.MovieDao;
import org.hhy.cloud.movie.entity.Movie;
import org.hhy.cloud.movie.service.MovieService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Description: 电影service实现
 *
 * @Author lht
 * @Date 2020/9/12 下午10:34
 **/
@Service
public class MovieServiceImpl extends BaseMybatisCrudServiceImpl<Movie,String, MovieDao> implements MovieService {
}
