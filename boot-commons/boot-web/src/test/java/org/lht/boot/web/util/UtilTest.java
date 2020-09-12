package org.lht.boot.web.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.lang.util.ReflectionUtil;
import org.lht.boot.web.domain.entity.Teacher;
import org.lht.boot.web.domain.entity.User;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/1/11 6:52 PM
 **/
@Slf4j
public class UtilTest {


    /**
     * MapUtil测试
     */

    @Test
    public void testMap() {
        String sortName = "age";
        String order = "asc";

        List<User> users = Lists.newArrayList();
        users.add(new User());
        //.........


        users.sort((o1, o2) -> {
            Object o1Field = ReflectionUtil.getFieldValue(o1, sortName);
            Object o2Field = ReflectionUtil.getFieldValue(o1, sortName);
            return o1Field.toString().compareTo(o2Field.toString());
        });


        User user = new User();
        user.setName("李强");
        user.setAge(34);
        Map map = BeanUtils.objectToMap(user, false);
        log.info("map:{}", map);
    }

    @Test
    public void test02() throws NoSuchFieldException, IllegalAccessException {
        Teacher teacher = new Teacher();
        teacher.setName("lihaitao");
        Class<? extends Teacher> aClass = teacher.getClass();
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println(name.get(teacher));
    }
}
