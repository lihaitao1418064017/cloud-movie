package org.lht.boot.security.dao;

import org.lht.boot.security.entity.Role;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description RoleDao
 * @date 2020/3/25 19:50
 **/
@Repository
public interface RoleDao extends BaseMybatisPlusDao<Role> {

}
