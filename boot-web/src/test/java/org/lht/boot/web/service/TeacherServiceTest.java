package org.lht.boot.web.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lht.boot.web.api.param.Aggregation;
import org.lht.boot.web.api.param.AggregationEnum;
import org.lht.boot.web.api.param.AggregationParam;
import org.lht.boot.web.domain.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;


    @Test
    void delete() {
        teacherService.delete("null");
    }

    @Test
    void add() {
        Teacher teacher = new Teacher();
        teacher.setAge(25);
        teacher.setName("赵四");
        teacherService.insert(teacher);
    }

    @Test
    public void findOne() {
        Teacher one = teacherService.get("eba7494105d041bfa3629b10308d88d3");
        log.info("teacher---->{}", one);
    }

    @Test
    void update() {
        Teacher teacher = new Teacher();
        teacher.setName("Yuanchun");
        teacher.setAge(100);
        teacher.setId("eba7494105d041bfa3629b10308d88d3");
        teacherService.update(teacher);
    }

    @Test
    void patch() {
        Teacher teacher = new Teacher();
        teacher.setName("李海涛1");
        //        teacher.setAge(100);
        teacher.setId("eba7494105d041bfa3629b10308d88d3");
        teacherService.patch(teacher);


    }

    @Test
    void findAll() {
        List<Teacher> teachers = teacherService.get(Lists.newArrayList("eba7494105d041bfa3629b10308d88d3", "6adcb8c750ee401f9f34665f39422564"));
        log.info("teachers:{}", teachers);

    }

    @Test
    void getAll() {
        List<Teacher> all = teacherService.getAll();
        log.info("teachers:{}", all);
    }

    @Test
    void count() {
        long count = teacherService.count();
        log.info("count:{}", count);
    }


    @Test
    void Agg() {
        AggregationParam empty = AggregationParam.empty();
        empty.groupBy("sex");
        empty.aggregation(Aggregation.builder().field("age").as("maxAge").type(AggregationEnum.MAX).build());
        List<TeacherAggVO> select = teacherService.select(empty, TeacherAggVO.class);
        log.info("select:{}", select);
    }

}
