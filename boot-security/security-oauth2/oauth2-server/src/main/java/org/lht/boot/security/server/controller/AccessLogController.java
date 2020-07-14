package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.server.domain.entity.AccessLog;
import org.lht.boot.security.server.domain.vo.AccessLogVO;
import org.lht.boot.security.server.service.AccessLogService;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 日志接口
 * @author: LiHaitao
 * @date: 2020/7/14 14:36
 */
@RestController
@RequestMapping("/access_log")
@Api(tags = "日志接口", description = "提供日志相关的 Rest API")
@AccessLogger("访问日志")
public class AccessLogController extends AbstractController<AccessLog, String, AccessLogVO, AccessLogService> {


}
