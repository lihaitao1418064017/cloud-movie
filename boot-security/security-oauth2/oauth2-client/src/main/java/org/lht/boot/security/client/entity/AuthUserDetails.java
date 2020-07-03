package org.lht.boot.security.client.entity;

import lombok.Getter;
import org.lht.boot.security.core.entity.AuthRole;
import org.lht.boot.security.core.entity.OAuth2UserAuthentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/5/28 17:12
 */
public class AuthUserDetails implements UserDetails {


    private String defaultRolePrefix = "ROLE_";

    @Getter
    private OAuth2UserAuthentication authentication;

    public AuthUserDetails(OAuth2UserAuthentication authentication) {
        this.authentication = authentication;
    }

    public AuthUserDetails(OAuth2UserAuthentication authentication, String defaultRolePrefix) {
        this.authentication = authentication;
        this.defaultRolePrefix = defaultRolePrefix;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authentication.getPermissions();
    }

    public Set<AuthRole> getRoles() {
        return authentication.getRoles();
    }


    @Override
    public String getPassword() {
        return authentication.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return authentication.getUser().getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return authentication.getUser().isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return authentication.getUser().isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return authentication.getUser().isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return authentication.getUser().isEnabled();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthUserDetails that = (AuthUserDetails) o;

        return authentication.equals(that.authentication);
    }

    @Override
    public int hashCode() {
        return authentication.hashCode();
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
