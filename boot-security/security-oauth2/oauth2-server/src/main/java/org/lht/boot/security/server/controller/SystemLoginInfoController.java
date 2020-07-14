package org.lht.boot.security.server.controller;

import org.lht.boot.security.resource.entity.SystemLoginInfo;
import org.lht.boot.security.resource.service.SystemLoginInfoService;
import org.lht.boot.security.resource.vo.SystemLoginInfoVO;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description 系统访问
 * @date 2020/7/14 18:44
 **/
@RestController
@RequestMapping("/system_login")
@AccessLogger("系统访问记录")
public class SystemLoginInfoController extends AbstractController<SystemLoginInfo, Integer, SystemLoginInfoVO, SystemLoginInfoService> {
}
