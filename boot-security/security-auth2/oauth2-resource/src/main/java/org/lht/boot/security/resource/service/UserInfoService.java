package org.lht.boot.security.resource.service;

import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.service.BaseCrudService;

/**
 * @author LiHaitao
 * @description UserInfoService:
 * @date 2020/3/19 14:48
 **/
public interface UserInfoService extends BaseCrudService<UserInfo, Integer> {

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UserInfo selectByUsername(String username);

    /**
     * 分页查询，带角色信息
     *
     * @param queryParam
     * @return
     */
    PagerResult<UserVO> page(QueryParam queryParam);
}
