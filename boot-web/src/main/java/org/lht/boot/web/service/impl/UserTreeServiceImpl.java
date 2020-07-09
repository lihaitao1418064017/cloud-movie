package org.lht.boot.web.service.impl;

import org.lht.boot.web.dao.UserTreeDao;
import org.lht.boot.web.domain.entity.User;
import org.lht.boot.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/1/11 3:14 PM
 **/
@Service
public class UserTreeServiceImpl extends CrudTreeServiceImpl<User, String, UserTreeDao> implements UserService {

}