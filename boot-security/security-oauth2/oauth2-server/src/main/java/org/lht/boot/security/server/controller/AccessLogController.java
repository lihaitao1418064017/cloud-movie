package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.security.server.domain.entity.AccessLog;
import org.lht.boot.security.server.domain.vo.AccessLogVO;
import org.lht.boot.security.server.service.AccessLogService;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:用户接口
 *
 * @Author lht
 * @Date 2020/3/25 8:00 PM
 **/
@RestController
@RequestMapping("/access_log")
@Api(tags = "日志接口", description = "提供日志相关的 Rest API")
public class AccessLogController extends AbstractController<AccessLog, String, AccessLogVO, AccessLogService> {


}
