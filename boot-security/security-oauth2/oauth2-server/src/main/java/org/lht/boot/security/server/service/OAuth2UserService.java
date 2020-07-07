package org.lht.boot.security.server.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.cache.redis.RedisUtil;
import org.lht.boot.security.common.exception.SecException;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.PermissionService;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.server.domain.OAuth2UserInfoAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author LiHaitao
 * @description OAuth2UserService:用户信息服务类
 * @date 2020/5/28 15:24
 **/
@Service
@Slf4j
public class OAuth2UserService {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private RedisUtil<String, String> redisUtil;


    /**
     * 获取登录用户信息
     *
     * @param
     * @param clientId
     * @return
     */
    public OAuth2UserInfoAuthentication getLoginUser(String clientId, String accessToken) {
        Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clientId);
        if (CollectionUtil.isEmpty(tokensByClientId)) {
            throw new IllegalArgumentException("accessToken not exists");
        }

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new IllegalArgumentException("client not exists!");
        }
        if (accessToken == null) {
            throw new IllegalArgumentException("accessToken not exists!");
        }
        String username = redisUtil.get(accessToken);
        if (username == null) {
            throw new IllegalArgumentException("get a username on the accessToken failed!");
        }
        UserInfo userInfo = userInfoService.selectByUsername(username);
        if (ObjectUtil.isNull(userInfo)) {
            throw new SecException("The user does not exist and cannot be authorized");
        }
        OAuth2UserInfoAuthentication oAuth2UserAuthentication = new OAuth2UserInfoAuthentication();
        oAuth2UserAuthentication.setPermissions(new HashSet<>(permissionService.select(userInfo.getId())));
        oAuth2UserAuthentication.setRoles(new HashSet<>(roleService.select(userInfo.getId())));
        oAuth2UserAuthentication.setUser(userInfo);
        return oAuth2UserAuthentication;
    }


    //    /**
    //     * 获取角色资源
    //     *
    //     * @param userInfo
    //     * @return
    //     */
    //    private Set<Permission> findPermission(UserInfo userInfo) {
    //        List<UserRole> userRoles = this.userRoleService.select(QueryParam.build("user_id", userInfo.getId()));
    //        final List<Integer> rolePermissions = this.rolePermissionService
    //                .select(QueryParam.build("role_id"
    //                        , TermEnum.in
    //                        , userRoles
    //                                .stream()
    //                                .map(UserRole::getRoleId)
    //                                .collect(Collectors.toSet())))
    //                .stream()
    //                .map(RolePermission::getPermissionId)
    //                .collect(Collectors.toList());
    //        return new HashSet<>(this.permissionService
    //                .select(QueryParam.build("id"
    //                        , TermEnum.in
    //                        , rolePermissions)));
    //    }


}
