package org.lht.boot.security.server.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.lang.util.RandomUtils;
import org.lht.boot.security.resource.dao.UserInfoDao;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//import org.lht.boot.security.server.OAuth2ServerApplication;

/**
 * @author LiHaitao
 * @description UserInfoTest:
 * @date 2020/6/23 15:53
 **/
@Slf4j
@SpringBootTest
public class UserInfoTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserInfoService userInfoService;


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
        List<UserVO> userInfoDtos = userInfoDao.page(queryParam.getTerms(), page.getCurrent(), page.getSize());
        log.info("userInfoDtos:{}", userInfoDtos);
    }

    @Test
    public void test02() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("lihaitao");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", "17809269791");
        userInfoDao.update(userInfo, queryWrapper);
    }


    @Test
    public void test03() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("lihaitao1");
        UpdateParam updateParam = new UpdateParam();
        updateParam.setData(userInfo);
        updateParam.addTerm(Term.build("username", "17809269791"));
        userInfoService.patch(updateParam);
    }


    @Test
    public void test04() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("creatorUser", "1112");
        UserInfo userInfo = userInfoDao.selectOne(queryWrapper);
        log.info("info:{}", userInfo);
    }

}
