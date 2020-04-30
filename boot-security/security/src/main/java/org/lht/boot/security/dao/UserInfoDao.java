package org.lht.boot.security.dao;

import org.lht.boot.security.entity.User;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description UserDao:
 * @date 2020/3/19 13:50
 **/
@Repository
public interface UserInfoDao extends BaseMybatisPlusDao<User> {


}
