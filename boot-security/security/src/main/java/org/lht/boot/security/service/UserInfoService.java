package org.lht.boot.security.service;

import org.lht.boot.security.entity.UserInfo;
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
}
