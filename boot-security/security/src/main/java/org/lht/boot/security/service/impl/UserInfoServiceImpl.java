package org.lht.boot.security.service.impl;

import org.lht.boot.security.dao.UserInfoDao;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.service.UserInfoService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserInfoServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class UserInfoServiceImpl extends BaseMybatisCrudServiceImpl<User, Integer, UserInfoDao> implements UserInfoService {

}
