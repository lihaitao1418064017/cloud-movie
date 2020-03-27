package org.lht.boot.security.service.impl;

import org.lht.boot.security.dao.UserDao;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.service.UserService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class UserServiceImpl extends BaseMybatisCrudServiceImpl<User, Integer, UserDao> implements UserService {

}
