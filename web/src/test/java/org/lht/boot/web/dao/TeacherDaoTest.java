package org.lht.boot.web.dao;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
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
            teacher.setGrade(RandomUtil.randomString("初高研博小", 1)
                    + RandomUtil.randomString("一二三", 1) + "("
                    + RandomUtil.randomString("123456789", 1) + ")班");
            if (i % 2 == 0) {
                teacher.setSex(0);
                teacher.setName(RandomUtil
                        .randomString("朱秦尤许何吕施孔曹严华金魏陶姜鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤李刘赵王周陈许金钱孙郑", 1) + RandomUtil
                        .randomString("强浩涛鹏雨青红伟旭刚桃倩庆娜珍家寒桃楠杉玲璟玲柏妮楠曼寒美琳雅初家萱华彤冬玲俊花丽瑶琬璇薇春格璟玉月丽鹤阳珠璇馨蕾岚锦美雪紫心彩阳璇珠曼云琬岚颖怡栀云琪雨琪柔雪璐杉欢璇韵歆蔚茹珊蔚冰曼薇薇雯格雅瑶欢枫芝可雅莉芳明雯香馨凌珍欢琳柔婷帛芙春美丽紫梓柔玥玉弦", 1));

            } else {
                teacher.setSex(1);
                teacher.setName(RandomUtil
                        .randomString("李刘赵王周陈许金钱孙郑", 1) + RandomUtil
                        .randomString("强浩涛鹏雨青红伟旭刚桃倩庆娜雪桃琳曼倩桐鑫芝雨慧薇莉心雨蔚彤菡钰莲玲梅云梅彤雅可栀碧琳云桃楠芸寒婷淑采雪璐杉欢璇韵歆蔚茹珊蔚冰曼薇薇雯格雅瑶欢枫芝可雅莉芳明雯香馨凌珍欢琳柔婷帛芙春美丽紫梓柔玥玉弦", 1) + RandomUtil
                        .randomString("珍家寒桃楠杉玲璟玲柏妮楠曼寒美琳雅初家萱华彤冬玲俊花丽瑶琬璇薇春格璟玉月丽鹤阳珠璇馨蕾岚锦美雪紫心彩阳璇珠曼云琬岚颖怡栀云琪雨琪柔杉昭芳惠函旭蕾珊茜怡璇", 1));
            }

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
