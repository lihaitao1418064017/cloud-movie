package org.lht.boot.security.server.service;

import org.lht.boot.security.config.constants.AuthoritiesEnum;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**

 * @description: 用户信息获取
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    /**
     * 通过 Username 加载用户详情
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("lihaitao")) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode("123456");
            UserDetails userDetails = new User("lihaitao",
                    password,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(AuthoritiesEnum.ADMIN.getRole()));
            return userDetails;
        }
        return null;
    }
}
