package org.lht.boot.web.controller;

import org.lht.boot.web.domain.entity.User;
import org.lht.boot.web.domain.vo.UserVO;
import org.lht.boot.web.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description UserController:
 * @date 2020/1/15 14:21
 **/
@RestController
@RequestMapping("/test_user")
public class UserController extends AbstractController<User, String, UserVO, UserService> {

}
