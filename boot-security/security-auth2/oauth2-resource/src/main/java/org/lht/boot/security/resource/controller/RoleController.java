package org.lht.boot.security.resource.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.resource.entity.Role;
import org.lht.boot.security.resource.service.RoleService;
import org.lht.boot.security.resource.vo.RoleVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:01 PM
 **/
@RestController
@RequestMapping("/role")
@Api(tags = "角色相关接口", description = "提供角色相关的 Rest API")
public class RoleController extends AbstractController<Role, Integer, RoleVO, RoleService> {
}
