package org.hhy.cloud.movie.controller;

import org.hhy.cloud.movie.entity.Actor;
import org.hhy.cloud.movie.service.ActorService;
import org.hhy.cloud.movie.vo.ActorVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 演员controller
 *
 * @Author lht
 * @Date 2020/9/12 下午10:37
 **/
@RestController
@RequestMapping("/actor")
public class ActorController extends AbstractController<Actor,String, ActorVO, ActorService> {

}
