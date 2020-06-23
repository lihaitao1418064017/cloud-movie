package org.lht.boot.security.resource.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

/**
 * @author LiHaitao
 * @description UserInfoDao:
 * @date 2020/3/19 13:50
 **/
@Repository
public interface UserInfoDao extends BaseMybatisPlusDao<UserInfo> {

    /**
     * 分页查询，带角色信息
     *
     * @param page
     * @return
     */
    Page<UserVO> selectPage(@Param("page") Page<UserVO> page, @Param("queryParam") QueryParam queryParam);
}
