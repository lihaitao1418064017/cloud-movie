package org.hhy.cloud.movie.service.impl;

import org.hhy.cloud.movie.dao.ActorDao;
import org.hhy.cloud.movie.entity.Actor;
import org.hhy.cloud.movie.service.ActorService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Description: 演员service实现
 *
 * @Author lht
 * @Date 2020/9/12 下午10:34
 **/
@Service
public class ActorServiceImpl extends BaseMybatisCrudServiceImpl<Actor,String, ActorDao> implements ActorService {
}
