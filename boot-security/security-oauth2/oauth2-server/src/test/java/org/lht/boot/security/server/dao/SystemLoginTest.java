package org.lht.boot.security.server.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.security.server.domain.entity.SystemLoginInfo;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author LiHaitao
 * @description
 * @date 2020/7/20 10:42
 **/
@Slf4j
@SpringBootTest
public class SystemLoginTest {

    @Autowired
    private SystemLoginInfoDao dao;

    @Test
    public void test01() {
        QueryParam queryParam = new QueryParam();
        queryParam.addTerm(Term.build("id", "4d865ca25fc54305a07db6c3052123"));
        List<SystemLoginInfo> select = dao.select(queryParam);
    }

    @Test
    public void test02() {
        SystemLoginInfo systemLoginInfo = new SystemLoginInfo();
        systemLoginInfo.setId("4d865ca25fc54305a07db6c3052123");
        systemLoginInfo.setCreateTime(10000000000000000L);
        dao.patch(systemLoginInfo);
    }

    @Test
    public void test04() {
        SystemLoginInfo systemLoginInfo = new SystemLoginInfo();
        systemLoginInfo.setId("4d865ca25fc54305a07db6c3052123");
        systemLoginInfo.setCreateTime(10000000000000000L);
        dao.add(systemLoginInfo);
    }


}
