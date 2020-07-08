package org.lht.boot.security.client.controller;

import org.lht.boot.security.core.common.util.OAuth2AuthenticationUtil;
import org.lht.boot.security.core.vo.AuthPermissionVO;
import org.lht.boot.security.core.vo.AuthUserVO;
import org.lht.boot.security.core.vo.AuthenticationVO;
import org.lht.boot.security.entity.AuthRole;
import org.lht.boot.security.entity.AuthUser;
import org.lht.boot.security.entity.AuthUserDetails;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.BeanUtils;
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
        AuthUserDetails details = (AuthUserDetails) authentication.getPrincipal();
        AuthenticationVO authenticationVO = new AuthenticationVO();
        AuthUser user = details.getAuthentication().getUser();
        AuthUserVO authUserVO = new AuthUserVO();
        org.springframework.beans.BeanUtils.copyProperties(user, authUserVO);
        authenticationVO.setUser(authUserVO);
        details.getAuthentication().getPermissions();
        Set<AuthPermissionVO> authPermissionVOS = details.getAuthentication().getPermissions().stream().map(permission -> {
            AuthPermissionVO authPermissionVO = new AuthPermissionVO();
            BeanUtils.copyProperties(permission, authPermissionVO);
            return authPermissionVO;
        }).collect(Collectors.toSet());
        authenticationVO.setPermissionVOS(authPermissionVOS);
        Set<AuthRole> roles = details.getAuthentication().getRoles();
        authenticationVO.setRoleSigns(roles.stream().map(AuthRole::getSign).collect(Collectors.toSet()));
        return R.ok(authenticationVO);
    }
}
