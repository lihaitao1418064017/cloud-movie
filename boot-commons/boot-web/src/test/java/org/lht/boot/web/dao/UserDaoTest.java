package org.lht.boot.web.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Sort;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserDaoTest {


    @Autowired
    private UserDao userDao;


    @Test
    void delete() {
        //        userDao.delete("");
    }

    @Test
    void add() {
        QueryWrapper queryWrapper = new QueryWrapper();
        //        userDao.selectPage()
        //       userDao.();

    }

    @Test
    public void selectPage() {
        QueryParam queryParam = new QueryParam();
        queryParam.and(Term.build("age", 1234));
        queryParam.setPageSize(5);
        PagerResult<User> page = userDao.selectPage(queryParam);
        log.info("page:{}", page);
    }

    @Test
    public void sort() {
        QueryParam queryParam = new QueryParam();
        queryParam.setPageSize(10);
        Sort sort = new Sort();
        sort.desc("age");
        queryParam.sort(Lists.newArrayList(sort));
        PagerResult<User> page = userDao.selectPage(queryParam);
        log.info("page:{}", page);

    }

}
