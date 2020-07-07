package org.lht.boot.security.server.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.common.SecUserDetails;
import org.lht.boot.security.core.entity.OAuth2UserAuthentication;
import org.lht.boot.security.entity.AuthPermission;
import org.lht.boot.security.entity.AuthRole;
import org.lht.boot.security.entity.AuthUser;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.PermissionService;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.server.common.constant.OAuth2UserConstant;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author LiHaitao
 * @description 用户登陆加载权限
 * @date 2020/3/25 19:50
 **/
@Slf4j
@Configuration
@SuppressWarnings("all")
public class OAuth2SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = this.userInfoService.selectSingle(QueryParam.build(Term.build("username", username)));
        if (userInfo != null) {
            final List<String> roles = findRole(userInfo);
            final List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            boolean notLocked = false;
            //账户锁定判断
            if (OAuth2UserConstant.STATUS_VALID.equals(userInfo.getStatus())) {
                notLocked = true;
            }
            log.info("userInfo:{} ,password:{}", userInfo.getUsername(), passwordEncoder.encode("123456"));

            OAuth2UserAuthentication oAuth2UserAuthentication = new OAuth2UserAuthentication();
            List<Permission> permissions = permissionService.select(userInfo.getId());
            oAuth2UserAuthentication.setPermissions(permissions.stream().map(permission -> {
                AuthPermission authPermission = new AuthPermission();
                BeanUtils.copyProperties(permission, authPermission);
                return authPermission;
            }).collect(Collectors.toSet()));
            List<Role> roleList = roleService.select(userInfo.getId());
            oAuth2UserAuthentication.setRoles(roleList.stream().map(role -> {
                AuthRole authRole = new AuthRole();
                BeanUtils.copyProperties(role, authRole);
                return authRole;
            }).collect(Collectors.toSet()));
            AuthUser authUser = new AuthUser();
            BeanUtils.copyProperties(userInfo, authUser);
            oAuth2UserAuthentication.setUser(authUser);
            oAuth2UserAuthentication.setPassword(userInfo.getPassword());
            SecUserDetails userDetails = new SecUserDetails(oAuth2UserAuthentication);
            userDetails.setLoginTime(DateUtil.formatDate(new Date()));
            return userDetails;
        } else {
            throw new UsernameNotFoundException("用戶不存在");
        }
    }


    /**
     * 获取角色
     *
     * @param userInfo
     * @return
     */
    private List<String> findRole(UserInfo userInfo) {
        return roleService
                .select(userInfo.getId())
                .stream()
                .map(Role::getSign)
                .collect(Collectors.toList());
    }

}
