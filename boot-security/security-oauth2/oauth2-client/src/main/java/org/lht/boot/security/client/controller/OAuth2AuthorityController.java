package org.lht.boot.security.client.controller;

import com.google.common.collect.Lists;
import org.lht.boot.security.client.entity.AuthUserDetails;
import org.lht.boot.security.core.common.util.OAuth2AuthenticationUtil;
import org.lht.boot.security.core.entity.AuthResource;
import org.lht.boot.security.core.entity.AuthRole;
import org.lht.boot.security.core.vo.AuthenticationVO;
import org.lht.boot.web.api.param.R;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description OAuth2AuthorityController:用户信息获取
 * @date 2020/7/1 10:59
 **/
@RequestMapping("/authority")
@RestController
public class OAuth2AuthorityController {


    @GetMapping("/me")
    public R me() {
        PreAuthenticatedAuthenticationToken authentication = (PreAuthenticatedAuthenticationToken) OAuth2AuthenticationUtil.getAuthentication();
        AuthUserDetails details = (AuthUserDetails) authentication.getDetails();

        AuthenticationVO authenticationVO = new AuthenticationVO();
        authenticationVO.setUser(details.getAuthentication().getUser());
        Set<AuthResource> permissions = details.getAuthentication().getPermissions();
        authenticationVO.setResources(Lists.newArrayList(permissions));
        Set<AuthRole> roles = details.getAuthentication().getRoles();
        authenticationVO.setRoleSigns(roles.stream().map(AuthRole::getSign).collect(Collectors.toSet()));
        return R.ok(authenticationVO);
    }
}
