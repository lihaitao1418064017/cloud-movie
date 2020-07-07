package org.lht.boot.security.entity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: LiHaitao
 * @date: 2020/7/7 10:11
 */
public interface Authentication extends Serializable {

    AuthUser getUser();

    String getPassword();

    Set<AuthRole> getRoles();

    Set<String> getRoleSigns();

    Set<String> getPermissionSigns();

    Set<AuthPermission> getPermissions();

    default Optional<Set<AuthRole>> getRoles(String sign) {
        if (null == sign) {
            return Optional.ofNullable(null);
        } else {
            Set<AuthRole> roles = (Set<AuthRole>) this.getRoles().stream().filter((role) -> {
                return StringUtils.equals(role.getSign(), sign);
            }).collect(Collectors.toSet());
            return CollectionUtils.isEmpty(roles) ? Optional.ofNullable(null) : Optional.of(roles);
        }
    }

    default boolean hasRole(String sign) {
        return !this.getRoles(sign).isPresent();
    }


}
