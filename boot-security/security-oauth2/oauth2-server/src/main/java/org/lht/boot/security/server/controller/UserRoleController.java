package org.lht.boot.security.server.controller;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.Api;
import org.lht.boot.lang.util.R;
import org.lht.boot.security.resource.entity.UserRole;
import org.lht.boot.security.resource.service.UserRoleService;
import org.lht.boot.security.resource.vo.UserRoleVO;
import org.lht.boot.web.common.annotation.AccessLogger;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:用户和角色相关接口
 *
 * @Author lht
 * @Date 2020/3/25 8:00 PM
 **/
@RestController
@RequestMapping("/user_role")
@Api(tags = "用户和角色相关接口", description = "提供角色和角色相关的 Rest API")
@AccessLogger("用户角色关联模块")
public class UserRoleController extends AbstractController<UserRole, Integer, UserRoleVO, UserRoleService> {


    /**
     * 添加或更新
     *
     * @param vos 角色和用户id关联vo
     * @return 保存结果
     */
    @PutMapping("/saveOrUpdate")
    public R<Integer> saveOrUpdate(@RequestBody List<UserRoleVO> vos) {
        if (CollectionUtil.isEmpty(vos)) {
            return R.ok();
        }
        return R.ok(service.saveOrUpdate(vosToEntities(vos)));
    }
}
