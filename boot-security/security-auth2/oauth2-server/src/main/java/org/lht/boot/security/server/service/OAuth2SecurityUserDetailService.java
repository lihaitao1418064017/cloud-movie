package org.lht.boot.security.server.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.common.SecUserDetails;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.service.UserRoleService;
import org.lht.boot.security.server.common.constant.OAuth2UserConstant;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;
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
    private UserRoleService userRoleService;


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
            log.info("userInfo:{} ,password:{}", userInfo.getUsername(), passwordEncoder.encode("oauth2"));
            SecUserDetails userDetails = new SecUserDetails(userInfo.getUsername()
                    , userInfo.getPassword()
                    , true
                    , true
                    , true
                    , notLocked
                    , simpleGrantedAuthorities);

            userDetails.setUserId(userInfo.getId());
            userDetails.setPassword(userInfo.getPassword());
            userDetails.setUsername(userInfo.getUsername());
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
        List<UserRole> userRoles = this.userRoleService.select(QueryParam.build("user_id", userInfo.getId()));
        return roleService
                .select(QueryParam
                        .build("id", TermEnum.in,
                                userRoles
                                        .stream()
                                        .map(UserRole::getRoleId)
                                        .collect(Collectors.toList())))
                .stream()
                .map(Role::getSign)
                .collect(Collectors.toList());
    }

}
