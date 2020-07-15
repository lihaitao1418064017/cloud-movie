package org.lht.boot.security.server.controller;

import org.lht.boot.lang.util.IpUtils;
import org.lht.boot.lang.util.ServletUtil;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.security.server.domain.vo.SystemLoginInfoVO;
import org.lht.boot.security.server.service.SystemLoginInfoService;
import org.lht.boot.web.api.param.R;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author LiHaitao
 * @description 系统访问
 * @date 2020/7/14 18:44
 **/
@RestController
@RequestMapping("/system_login")
@AccessLogger("系统访问记录")
public class SystemLoginInfoController extends AbstractController<SystemLoginInfo, Integer, SystemLoginInfoVO, SystemLoginInfoService> {

    @Override
    public R<Integer> add(@RequestBody SystemLoginInfoVO data) {
        data.setAccessTime(new Date());
        data.setIpAddress(IpUtils.getIpAddr(ServletUtil.getServletRequest()));
        return R.ok(service.insert(voToEntity(data)));
    }
}
