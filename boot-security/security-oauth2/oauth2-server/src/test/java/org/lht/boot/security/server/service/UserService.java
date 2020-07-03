package org.lht.boot.security.server.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description UserService:
 * @date 2020/6/29 17:12
 **/
@SpringBootTest
@Slf4j
public class UserService {


    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test01() {
        QueryParam queryParam = new QueryParam();
        queryParam.addTerm(Term.build("age", 23));
        PagerResult<UserVO> page = userInfoService.page(queryParam);
        log.info("userinfos:{}", page.getRecords());
    }
}
