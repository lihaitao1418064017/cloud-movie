package org.lht.boot.web.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.lht.boot.web.WebApplication;
import org.lht.boot.web.api.param.*;
import org.lht.boot.web.api.param.util.ParamEsUtil;
import org.lht.boot.web.domain.entity.Teacher;
import org.lht.boot.web.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = WebApplication.class)
@Slf4j
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;


    @Test
    public void delete() {
        teacherService.delete("null");
    }

    @Test
    public void add() {
        Teacher teacher = new Teacher();
        teacher.setAge(25);
        teacher.setName("赵四");
        User user = new User();
        user.setAge(12);
        user.setName("lihaitao");
        teacher.setUser(user);
        teacherService.insert(teacher);
    }

    @Test
    public void findOne() {
        Teacher one = teacherService.get("eba7494105d041bfa3629b10308d88d3");
        log.info("teacher---->{}", one);
    }

    @Test
    public void update() {
        Teacher teacher = new Teacher();
        teacher.setName("Yuanchun");
        teacher.setAge(100);
        teacher.setId("eba7494105d041bfa3629b10308d88d3");
        teacherService.update(teacher);
    }

    @Test
    public void patch() {
        Teacher teacher = new Teacher();
        teacher.setName("李海涛1");
        //        teacher.setAge(100);
        teacher.setId("eba7494105d041bfa3629b10308d88d3");
        teacherService.patch(teacher);


    }

    @Test
    public void findAll() {
        List<Teacher> teachers = teacherService.get(Lists.newArrayList("eba7494105d041bfa3629b10308d88d3", "6adcb8c750ee401f9f34665f39422564"));
        log.info("teachers:{}", teachers);

    }

    @Test
    public void getAll() {
        List<Teacher> all = teacherService.getAll();
        log.info("teachers:{}", all);
    }

    @Test
    public void count() {
        long count = teacherService.count();
        log.info("count:{}", count);
    }


    @Test
    public void Agg() {
        AggregationParam empty = AggregationParam.empty();
        //        empty.groupBy("sex");
        empty.aggregation(Aggregation.builder().field("age").as("maxAge").type(AggregationEnum.AVG).build());
        List<TeacherAggVO> select = teacherService.select(empty, TeacherAggVO.class);
        log.info("select:{}", select);
    }


    @Test
    public void nested() {
        BoolQueryBuilder tempBuilder = ParamEsUtil.buildSearchQueryBuilder(QueryParam.empty());
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(QueryBuilders.nestedQuery("nested", tempBuilder, ScoreMode.None));


    }

    @Test
    public void selectPage() {
        List<Teacher> select = teacherService.select(QueryParam.empty().build(Term.build("user.age", 13)));
        log.info("select :{}", select);
    }

}
