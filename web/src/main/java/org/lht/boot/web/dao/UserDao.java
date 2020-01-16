package org.lht.boot.web.dao;

import org.lht.boot.web.domain.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description UserDao:
 * @date 2020/1/2 17:22
 **/
@Repository
public interface UserDao extends AbstractMybatisPlusDao<User> {


}
