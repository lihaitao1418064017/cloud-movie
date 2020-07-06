package org.lht.boot.security.server.controller;

import org.lht.boot.security.core.common.util.OAuth2AuthenticationUtil;
import org.lht.boot.security.core.vo.AuthenticationVO;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.PermissionService;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.web.api.param.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiHaitao
 * @description OAuth2AuthorityController:用户信息获取
 * @date 2020/7/1 10:59
 **/
@RequestMapping("/authority")
@RestController
public class OAuth2AuthorityController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/me")
    public R me() {
        Authentication authentication = OAuth2AuthenticationUtil.getAuthentication();
        String username = (String) authentication.getPrincipal();
        UserInfo userInfo = userInfoService.selectByUsername(username);
        AuthenticationVO authenticationVO = new AuthenticationVO();

        List<Permission> permissions = permissionService.select(userInfo.getId());//todo 这里需要做成资源树
        //                authenticationVO.setResources();

        return R.ok();
    }


}
