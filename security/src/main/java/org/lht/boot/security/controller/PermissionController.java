package org.lht.boot.security.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.entity.Permission;
import org.lht.boot.security.service.PermissionService;
import org.lht.boot.security.vo.PermissionVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/3/25 8:07 PM
 **/
@RestController
@RequestMapping("/permission")
@Api(tags = "权限相关接口", description = "提供权限相关的 Rest API")
public class PermissionController extends AbstractController<Permission,Integer, PermissionVO, PermissionService> {
}
