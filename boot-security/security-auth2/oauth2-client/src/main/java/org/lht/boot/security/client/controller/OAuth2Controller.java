package org.lht.boot.security.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.lht.boot.security.client.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiHaitao
 * @description OAuth2Controller:
 * @date 2020/4/28 19:33
 **/
@RestController
@RequestMapping("/oauth2")
@Slf4j
public class OAuth2Controller {


    @Autowired
    private OAuth2Service oAuth2Service;

    @RequestMapping("/authorize")
    public ModelAndView authorize(HttpServletRequest request, HttpServletResponse response) {
        try {
            String applyForTokenUri = oAuth2Service.authorize(request, response);
            return new ModelAndView(new RedirectView(applyForTokenUri));
        } catch (Exception e) {
            return null;
        }
    }
}
