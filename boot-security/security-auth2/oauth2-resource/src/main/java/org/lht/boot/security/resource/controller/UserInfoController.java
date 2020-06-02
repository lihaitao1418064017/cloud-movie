package org.lht.boot.security.resource.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserVO;
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
public class UserInfoController extends AbstractController<UserInfo, Integer, UserVO, UserInfoService> {

}
