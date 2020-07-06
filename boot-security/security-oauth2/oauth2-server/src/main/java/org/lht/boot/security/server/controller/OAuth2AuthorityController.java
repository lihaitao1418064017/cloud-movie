package org.lht.boot.security.server.controller;

import org.lht.boot.security.core.common.util.OAuth2AuthenticationUtil;
import org.lht.boot.web.api.param.R;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Authentication authentication = OAuth2AuthenticationUtil.getAuthentication();
        String username = (String) authentication.getPrincipal();
        return R.ok();
    }


}
