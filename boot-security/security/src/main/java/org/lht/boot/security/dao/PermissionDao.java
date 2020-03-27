package org.lht.boot.security.dao;

import org.lht.boot.security.entity.Permission;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description PermissionDao:
 * @date 2020/3/19 13:53
 **/
@Repository
public interface PermissionDao extends BaseMybatisPlusDao<Permission> {

}
