package org.lht.boot.security.server.dao;

import com.google.common.collect.Lists;
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
        queryParam.addTerms(Lists.newArrayList(Term.build("msg", "登出成功")));
        queryParam.addTerm(Term.build("username", "123456"));
        List<SystemLoginInfo> select = dao.select(queryParam);
    }
}
