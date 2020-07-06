package org.lht.boot.security.server.dao;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.lht.boot.security.resource.entity.Permission;
import org.lht.boot.security.resource.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/6 16:54
 **/
@SpringBootTest
public class PermissionTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    public void test01() {
        List<Permission> select = permissionService.select(1);
        Assert.notEmpty(select);
    }
}
