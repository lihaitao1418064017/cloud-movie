package org.lht.boot.security.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.entity.User;
import org.lht.boot.security.service.UserService;
import org.lht.boot.security.vo.UserVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:00 PM
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
public class UserController extends AbstractController<User,Integer, UserVO, UserService> {

}