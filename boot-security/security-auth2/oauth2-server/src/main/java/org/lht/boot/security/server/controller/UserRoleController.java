package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.security.resource.service.UserRoleService;
import org.lht.boot.security.resource.vo.UserRoleVO;
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
@RequestMapping("/user_role")
@Api(tags = "用户和角色相关接口", description = "提供角色和角色相关的 Rest API")
public class UserRoleController extends AbstractController<UserRole, Integer, UserRoleVO, UserRoleService> {

}
