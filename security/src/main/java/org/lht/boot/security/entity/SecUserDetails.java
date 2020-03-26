package org.lht.boot.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
/**
 * @author LiHaitao
 * @description 用户登陆信息
 * @date 2020/3/25 19:50
 **/
@Data
public class SecUserDetails extends User {

    private static final long serialVersionUID = 2017845963758421135L;

    private LoginType loginType = LoginType.NORMAL;

    private String remoteAddress;

    private String username;

    private String avatar;

    private Integer userId;

    private String email;

    private String mobile;

    private String password;

    private String loginTime;

    public SecUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
