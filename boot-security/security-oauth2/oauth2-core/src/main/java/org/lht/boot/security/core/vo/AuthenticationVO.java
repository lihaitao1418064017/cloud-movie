package org.lht.boot.security.core.vo;

import com.google.common.collect.Sets;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 权限信息
 * @author: LiHaitao
 * @date: 2020/7/6 17:19
 */
@Data
public class AuthenticationVO {
    /**
     * 用户信息
     */
    private AuthUserVO user;
    /**
     * 角色信息
     */
    private Set<String> roleSigns;
    /**
     * 权限资源信息
     */
    private Set<String> permissionSigns;
    /**
     * 权限资源信息
     */
    private Set<AuthPermissionVO> permissionVOS;


    public Set<String> getPermissionSigns() {
        if (this.permissionSigns == null) {
            synchronized (this) {
                if (this.permissionSigns == null) {
                    Set<String> resourceSigns = Sets.newHashSet();
                    //                    this.fill(resourceSigns, this.resources);
                    this.permissionSigns = permissionVOS.stream().map(AuthPermissionVO::getSign).collect(Collectors.toSet());
                }
            }
        }

        return this.permissionSigns;
    }

    //    private void fill(Set<String> resourceSigns, List<AuthPermissionVO> resources) {
    //        if (!CollectionUtils.isEmpty(resources)) {
    //            resources.stream().forEach((resource) -> {
    //                resourceSigns.add(resource.getSign());
    //                this.fill(resourceSigns, resource.getChildren());
    //            });
    //        }
    //    }


}
