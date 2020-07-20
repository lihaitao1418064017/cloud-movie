package org.lht.boot.security.server.util;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

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
}
