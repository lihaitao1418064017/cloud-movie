package org.lht.boot.security.controller;

import org.lht.boot.lang.util.ValidatorUtil;
import org.lht.boot.security.common.config.SecProperties;
import org.lht.boot.security.common.util.JWTTokenUtils;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.service.UserService;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author LiHaitao
 * @description LoginController: 登陆控制器
 * @date 2020/3/19 14:57
 **/
@RestController
@RequestMapping
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    private RedirectStrategy redirectStrategy;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestCache requestCache;

    @Autowired
    private SecProperties secProperties;


    //    8888888888888888888888888888888888888888888888888888888888888888888888888

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}", redirectUrl);
        }
        return "login";
    }

    @GetMapping("/")
    public void success(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, secProperties.getIndexUrl());
    }



    // 8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888

    @RequestMapping("/auth/login")
    @ResponseBody
    public String showLogin(String username, String password, HttpServletResponse httpResponse) throws Exception {
        //通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //如果认证对象不为空
        if (Objects.nonNull(authenticationToken)) {
            User user = userService.selectSingle(QueryParam.build(Term.build("username", username)));
            ValidatorUtil.notNull(user, "用户不存在");
        }
        try {
            //通过 AuthenticationManager（默认实现为ProviderManager）的authenticate方法验证 Authentication 对象
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //将 Authentication 绑定到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成Token
            String token = jwtTokenUtils.createToken(authentication, false);
            //将Token写入到Http头部
            httpResponse.addHeader("cookies", "Bearer " + token);
            return "/admin";
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

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasPermission('/user','user')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

}
