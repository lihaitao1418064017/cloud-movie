package org.lht.boot.security.server.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.security.resource.dao.UserInfoDao;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.security.server.OAuth2ServerApplication;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description UserInfoDaoTest:
 * @date 2020/6/23 15:53
 **/
@Slf4j
@SpringBootTest(classes = OAuth2ServerApplication.class)
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void test01() {
        Page<UserVO> page = new Page(1, 10);
        Param queryParam = new Param();
        queryParam.addTerm(Term.build("password", "123456"));
        IPage<UserVO> userInfoDtos = userInfoDao.selectPage(page, queryParam.getTerms());
        log.info("userInfoDtos:{}", userInfoDtos);
    }
}
