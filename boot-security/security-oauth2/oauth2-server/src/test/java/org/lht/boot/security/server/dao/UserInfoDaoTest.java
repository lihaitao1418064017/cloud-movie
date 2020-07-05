package org.lht.boot.security.server.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.lang.util.RandomUtils;
import org.lht.boot.security.resource.dao.UserInfoDao;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.vo.UserVO;
//import org.lht.boot.security.server.OAuth2ServerApplication;
import org.lht.boot.web.api.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description UserInfoDaoTest:
 * @date 2020/6/23 15:53
 **/
@Slf4j
@SpringBootTest
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;


    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("136" + RandomUtils.randomString("1234567890", 8));
            userInfo.setAge(RandomUtils.randomInt(20, 40));
            userInfo.setIdentify(RandomUtils.randomString("1234567890X", 15));
            userInfo.setEmail(RandomUtils.randomString("1234567890", 8) + "@qq.com");
            userInfo.setPhone(userInfo.getUsername());
            userInfo.setPassword("123456");
            userInfo.setName(RandomUtils.randomName());
            userInfo.setSex(Integer.valueOf(RandomUtils.randomSex(false)));
            QueryParam username = QueryParam.empty().addTerm(Term.build("username", userInfo.getUsername()));
            PagerResult<UserInfo> userInfoPagerResult = userInfoDao.selectPage(username);
            if (CollectionUtil.isEmpty(userInfoPagerResult.getRecords()))
                userInfoDao.insert(userInfo);
        }
    }

    @Test
    public void test01() {
        PagerResult<UserVO> page = new PagerResult<>(0, 10);
        Param queryParam = new Param();
        queryParam.addTerm(Term.build("age", TermEnum.nin, Lists.newArrayList(23, 24, 25)));
        log.info("term:{}", queryParam.getTerms().get(0).getTermType().getValue());
        IPage<UserVO> userInfoDtos = userInfoDao.page(page, queryParam.getTerms());
        log.info("userInfoDtos:{}", userInfoDtos.getRecords());
    }
}
