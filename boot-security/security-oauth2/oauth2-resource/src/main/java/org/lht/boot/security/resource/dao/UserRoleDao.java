package org.lht.boot.security.resource.dao;

import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHaitao
 * @description UserRoleDao:
 * @date 2020/3/19 14:31
 **/
@Repository
public interface UserRoleDao extends BaseMybatisPlusDao<UserRole> {

    /**
     * 更新或添加，根据user_id和role_id联合唯一索引
     *
     * @param
     * @return
     */
    Integer saveOrUpdate(@Param("userRoles") List<UserRole> userRoles);
}
