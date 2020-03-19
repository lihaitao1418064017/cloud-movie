package org.lht.boot.security.service;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.entity.Role;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.entity.UserRole;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiHaitao
 * @description CustomUserDetailService:
 * @date 2020/3/19 15:33
 **/
@Configuration
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        User user = userService.selectSingle(QueryParam.build(Term.build("username", username)));
        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // TODO: 2020/3/19 修复实体字段和数据库字段映射问题
        // 添加权限
        List<UserRole> userRoles = userRoleService.select(QueryParam.build(Term.build("user_id", user.getId())));
        final String encode = passwordEncoder.encode("123123");
        log.info("encode>>>>>>>>>>>{}", encode);

        for (UserRole userRole : userRoles) {
            //            final List<RolePermission> rolePermissions = rolePermissionService.select(QueryParam.build(Term.build("roleId", userRole.getRoleId())));
            //            for (RolePermission rolePermission : rolePermissions) {
            //                final Permission permission = permissionService.get(rolePermission.getPermissionId());

            final Role role = roleService.selectSingle(QueryParam.build(Term.build("id", userRole.getRoleId())));
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            //            }
        }
        // 返回UserDetails实现类
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
