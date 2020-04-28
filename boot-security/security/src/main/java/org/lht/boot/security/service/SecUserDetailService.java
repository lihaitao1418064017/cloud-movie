package org.lht.boot.security.service;

import cn.hutool.core.date.DateUtil;
import org.lht.boot.security.entity.Role;
import org.lht.boot.security.entity.SecUserDetails;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.entity.UserRole;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.api.param.TermEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author LiHaitao
 * @description 用户登陆加载权限
 * @date 2020/3/25 19:50
 **/
@Configuration
public class SecUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    private static final Integer STATUS_VALID = 1;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = this.userService.selectSingle(QueryParam.build(Term.build("username", username)));
        if (user != null) {
            final List<String> roles = findRole(user);
            final List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            boolean notLocked = false;
            //账户锁定判断
            if (STATUS_VALID.equals(user.getStatus())) {
                notLocked = true;
            }
            SecUserDetails userDetails = new SecUserDetails(user.getUsername()
                    , user.getPassword()
                    , true
                    , true
                    , true
                    , notLocked
                    , simpleGrantedAuthorities);

            userDetails.setUserId(user.getId());
            userDetails.setPassword(user.getPassword());
            userDetails.setUsername(user.getUsername());
            userDetails.setLoginTime(DateUtil.formatDate(new Date()));
            return userDetails;
        } else {
            throw new UsernameNotFoundException("用戶不存在");
        }
    }


    private List<String> findRole(User user) {
        List<UserRole> userRoles = this.userRoleService
                .select(QueryParam.build("user_id", user.getId()));
        return roleService.select(QueryParam
                .build("id", TermEnum.in,
                        userRoles
                                .stream()
                                .map(UserRole::getRoleId)
                                .collect(Collectors.toList())))
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

}
