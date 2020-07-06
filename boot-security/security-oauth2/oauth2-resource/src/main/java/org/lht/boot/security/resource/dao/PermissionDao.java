package org.lht.boot.security.resource.dao;

import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHaitao
 * @description PermissionDao:权限资源dao
 * @date 2020/3/19 13:53
 **/
@Repository
public interface PermissionDao extends BaseMybatisPlusDao<Permission> {

    /**
     * 根据用户名获取权限资源信息
     *
     * @param userId
     * @return
     */
    List<Permission> selectByUsername(@Param("userId") Integer userId);
}
