package org.lht.boot.security.server.util;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.lht.boot.security.server.common.util.LoginAddressUtil;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/20 13:25
 **/

public class ToolUtil {


    @Test
    public void test01() {
        String liHaiTao = StrUtil.toUnderlineCase("liHaiTao");
        System.out.println(liHaiTao);

    }

    @Test
    public void test02() {
        String ipInfo = LoginAddressUtil.getCity("223.104.145.125");
        System.out.println(ipInfo);
    }
}
