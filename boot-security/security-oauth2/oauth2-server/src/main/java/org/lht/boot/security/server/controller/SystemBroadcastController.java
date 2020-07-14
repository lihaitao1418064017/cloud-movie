package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.server.domain.entity.SystemBroadcast;
import org.lht.boot.security.server.domain.vo.SystemBroadcastVO;
import org.lht.boot.security.server.service.SystemBroadcastService;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 广播墙接口
 * @author: LiHaitao
 * @date: 2020/7/14 14:36
 */
@RestController
@RequestMapping("/system_broadcast")
@Api(tags = "广播墙", description = "提供广播墙相关的 Rest API")
@AccessLogger("广播墙")
public class SystemBroadcastController extends AbstractController<SystemBroadcast, String, SystemBroadcastVO, SystemBroadcastService> {


}
