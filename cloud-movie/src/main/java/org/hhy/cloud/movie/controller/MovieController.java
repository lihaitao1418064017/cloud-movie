package org.hhy.cloud.movie.controller;

import org.hhy.cloud.movie.entity.Movie;
import org.hhy.cloud.movie.service.MovieService;
import org.hhy.cloud.movie.vo.MovieVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 电影controller
 *
 * @Author lht
 * @Date 2020/9/12 下午10:37
 **/
@RestController
@RequestMapping("/movie")
public class MovieController extends AbstractController<Movie,String, MovieVO, MovieService> {

}
