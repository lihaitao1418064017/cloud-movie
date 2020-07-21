package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.lang.util.R;
import org.lht.boot.lang.util.ValidatorUtil;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.server.service.OAuth2SecurityUserDetailService;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author LiHaitao
 * @description LoginController: 登陆控制器
 * @date 2020/3/19 14:57
 **/
@RestController
@RequestMapping
@Api(tags = "登陆相关接口", description = "提供登陆相关的 Rest API")
public class LoginController {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OAuth2SecurityUserDetailService oAuth2SecurityUserDetailService;


    @GetMapping("/auth/login")
    public R showLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) throws Exception {
        //通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //如果认证对象不为空
        UserInfo userInfo = userInfoService.selectSingle(QueryParam.build(Term.build("username", username)));
        ValidatorUtil.notNull(userInfo, "用户不存在");
        try {
            authenticationToken.setDetails(oAuth2SecurityUserDetailService.loadUserByUsername(username));
            //通过 AuthenticationManager（默认实现为ProviderManager）的authenticate方法验证 Authentication 对象
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //将 Authentication 绑定到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return R.ok();
        } catch (BadCredentialsException authentication) {
            throw new Exception("密码错误");
        }

    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    //    @RequestMapping("/user")
    //    @ResponseBody
    //    @PreAuthorize("hasPermission('/user','user')")
    //    public String printUser() {
    //        return "如果你看见这句话，说明你有ROLE_USER角色";
    //    }

}
