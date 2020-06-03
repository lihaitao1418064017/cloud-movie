package org.lht.boot.security.server.service;

import cn.hutool.core.util.ObjectUtil;
import org.lht.boot.security.common.exception.SecException;
import org.lht.boot.security.resource.entity.*;
import org.lht.boot.security.resource.service.*;
import org.lht.boot.security.server.domain.entity.OAuth2UserAuthentication;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.TermEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description OAuth2UserService:用户信息服务类
 * @date 2020/5/28 15:24
 **/
@Service
public class OAuth2UserService {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取登录用户信息
     *
     * @param username
     * @param clientId
     * @return
     */
    public OAuth2UserAuthentication getLoginUser(String username, String clientId) {
        //检测用户
        UserInfo userInfo = userInfoService.selectByUsername(username);
        if (ObjectUtil.isNull(userInfo)) {
            throw new SecException("用户不存在");
        }
        OAuth2UserAuthentication oAuth2UserAuthentication = new OAuth2UserAuthentication();
        oAuth2UserAuthentication.setPermissions(findPermission(userInfo));
        oAuth2UserAuthentication.setRoles(findRole(userInfo));
        oAuth2UserAuthentication.setUserInfo(userInfo);
        return oAuth2UserAuthentication;
    }

    /**
     * 获取用户角色
     *
     * @param userInfo
     * @return
     */
    private Set<Role> findRole(UserInfo userInfo) {
        List<UserRole> userRoles = this.userRoleService
                .select(QueryParam.build("user_id", userInfo.getId()));
        return new HashSet<>(roleService.select(QueryParam
                .build("id", TermEnum.in,
                        userRoles
                                .stream()
                                .map(UserRole::getRoleId)
                                .collect(Collectors.toList()))));
    }


    /**
     * 获取角色资源
     *
     * @param userInfo
     * @return
     */
    private Set<Permission> findPermission(UserInfo userInfo) {
        List<UserRole> userRoles = this.userRoleService.select(QueryParam.build("user_id", userInfo.getId()));
        final List<Integer> rolePermissions = this.rolePermissionService
                .select(QueryParam.build("role_id"
                        , TermEnum.in
                        , userRoles
                                .stream()
                                .map(UserRole::getRoleId)
                                .collect(Collectors.toSet())))
                .stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        return new HashSet<>(this.permissionService
                .select(QueryParam.build("id"
                        , TermEnum.in
                        , rolePermissions)));
    }


}