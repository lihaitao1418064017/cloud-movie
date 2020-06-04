package org.lht.boot.web.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class UserServiceTest {


    @Autowired
    private UserService userService;


    @Test
    void delete() {
        userService.delete("2");
    }

    @Test
    void add() {
        User user = new User();
        user.setAge(23);
        user.setName("liuha");
        userService.insert(user);
    }

    @Test
    public void selectPage() {
        QueryParam queryParam = new QueryParam();
        queryParam.and(Term.build("age", 23));
        queryParam.setPageSize(5);
        List<User> page = userService.select(queryParam);
        log.info("page:{}", page);
    }

}
