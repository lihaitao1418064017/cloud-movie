package org.lht.boot.security.resource.dao;

import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.RolePermission;
import org.lht.boot.security.resource.service.RolePermissionService;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHaitao
 * @description RolePermissionDao:
 * @date 2020/3/19 14:45
 **/
@Repository
public interface RolePermissionDao extends BaseMybatisPlusDao<RolePermission> {

    /**
     * 更新或添加，根据permission_id和role_id联合唯一索引
     *
     * @param
     * @return
     */
    Integer saveOrUpdate(@Param("rolePermissions")List<RolePermission> rolePermissions);
}
