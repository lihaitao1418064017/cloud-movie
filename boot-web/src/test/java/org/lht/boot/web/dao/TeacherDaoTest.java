package org.lht.boot.web.dao;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.lang.util.RandomUtils;
import org.lht.boot.web.api.param.*;
import org.lht.boot.web.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class TeacherDaoTest {

    @Autowired
    private TeacherDao teacherEsDao;


    @Test
    void delete() {
        List<Teacher> all = teacherEsDao.findAll();
        List<String> collect = all.stream().map(Teacher::getId).collect(Collectors.toList());
        teacherEsDao.deleteById(collect);
    }

    @Test
    void add() {
        List<Teacher> teachers = Lists.newArrayList();
        int i = 10000;
        for (; i > 0; i--) {
            Teacher teacher = new Teacher();
            teacher.setAge(RandomUtil.randomInt(20, 80));
            teacher.setIdentify(RandomUtil.randomString("1234567890X", 15));
            teacher.setGrade(RandomUtils.randomGrade());
            teacher.setSex(RandomUtils.randomInt(2));
            teacher.setName(RandomUtils.randomName());
            teachers.add(teacher);
        }
        teacherEsDao.add(teachers);
    }

    @Test
    public void findOne() {
        Teacher one = teacherEsDao.findOne("d24d1f4b97ed4c3eb2d3a108cd94003c");
        log.info("teacher---->{}", one);
    }

    @Test
    void update() {
        Teacher teacher = new Teacher();
        teacher.setName("Yuanchun");
        teacher.setAge(100);
        teacher.setSex(1);
        teacher.setGrade("高一（2）班");
        teacher.setId("d24d1f4b97ed4c3eb2d3a108cd94003c");
        teacherEsDao.update(teacher);
    }

    @Test
    void updateEntitis() {
        Teacher teacher = new Teacher();
        teacher.setName("Yuanchun");
        teacher.setAge(88);
        teacher.setSex(1);
        teacher.setGrade("高一（2）班");
        teacher.setId("d24d1f4b97ed4c3eb2d3a108cd94003c");
        teacherEsDao.update(Lists.newArrayList(teacher));
    }

    @Test
    void patch() {
        Teacher teacher = new Teacher();
        teacher.setName("李海涛1");
        //        teacher.setAge(100);
        teacher.setId("d24d1f4b97ed4c3eb2d3a108cd94003c");
        teacherEsDao.patch(teacher);
    }

    @Test
    void findAll() {
        List<Teacher> teachers = teacherEsDao.findAll(Lists.newArrayList("eba7494105d041bfa3629b10308d88d3", "6adcb8c750ee401f9f34665f39422564"));
        log.info("teachers:{}", teachers);
    }

    @Test
    void getAll() {

    }

    @Test
    void selectByParam() {
        QueryParam queryParam = new QueryParam();
        queryParam.setPageSize(1000);
        queryParam.and(Term.build("age", 76));
        queryParam.addTerm(Term.build("sex", 1));
        queryParam.addTerm(Term.build("grade", "初三(5)班"));
        List<Teacher> teachers = teacherEsDao.select(queryParam);
        log.info("teachers:{}", teachers.size());
    }

    @Test
    void selectPage() {
        QueryParam queryParam = new QueryParam();
        queryParam.setPageSize(10);
        queryParam.setPageNo(1);
        //        queryParam.and(Term.build("age", 76));
        queryParam.addTerm(Term.build("sex", 1));
        //        queryParam.excludes("age", "sex");
        queryParam.setSorts(Lists.newArrayList(new Sort().desc("age")));
        queryParam.addTerm(Term.build("grade", TermEnum.like, "初三"));
        PagerResult<Teacher> teacherIPage = teacherEsDao.selectPage(queryParam);
        teacherIPage.getPages();
        log.info("teacherPage:{}", teacherIPage);
    }
}
