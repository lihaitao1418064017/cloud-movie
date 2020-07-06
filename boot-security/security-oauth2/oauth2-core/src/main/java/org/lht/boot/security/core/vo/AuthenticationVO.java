package org.lht.boot.security.core.vo;

import com.google.common.collect.Sets;
import lombok.Data;
import org.lht.boot.security.core.entity.AuthResource;
import org.lht.boot.security.core.entity.AuthUser;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

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
    private AuthUser user;
    /**
     * 角色信息
     */
    private Set<String> roleSigns;
    /**
     * 权限资源信息
     */
    private Set<String> resourceSigns;
    /**
     * 权限资源信息
     */
    private List<AuthResource> resources;


    public Set<String> getResourceSigns() {
        if (this.resourceSigns == null) {
            synchronized (this) {
                if (this.resourceSigns == null) {
                    Set<String> resourceSigns = Sets.newHashSet();
                    this.fill(resourceSigns, this.resources);
                    this.resourceSigns = resourceSigns;
                }
            }
        }

        return this.resourceSigns;
    }

    private void fill(Set<String> resourceSigns, List<AuthResource> resources) {
        if (!CollectionUtils.isEmpty(resources)) {
            resources.stream().forEach((resource) -> {
                resourceSigns.add(resource.getSign());
                this.fill(resourceSigns, resource.getChildren());
            });
        }
    }


}
