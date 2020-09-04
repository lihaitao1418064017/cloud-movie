package org.lht.boot.security.server.controller;

import io.swagger.annotations.Api;
import org.lht.boot.lang.util.R;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamServletUtil;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:用户接口
 *
 * @Author lht
 * @Date 2020/3/25 8:00 PM
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@AccessLogger("用户模块")
public class UserInfoController extends AbstractController<UserInfo, Integer, UserVO, UserInfoService> {

    /**
     * 用户详情
     *
     * @param request    request
     * @param queryParam 查询条件
     * @return 用户分页结果
     */
    @GetMapping("/detail")
    @AccessLogger("用户详情查询")
    public R<PagerResult<UserVO>> page(HttpServletRequest request, QueryParam queryParam) {
        ParamServletUtil.paddingTerms(queryParam, request);
        return R.ok(service.page(queryParam));
    }


}
