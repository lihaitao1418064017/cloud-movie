package com.xxl.job.executor.test.crud;

import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.hhy.xxl.CloudJobExecutorApplication;
import org.hhy.xxl.crud.dao.ActorDao;
import org.hhy.xxl.crud.dao.Dao;
import org.hhy.xxl.crud.dao.MysqlCurdDao;
import org.hhy.xxl.job.executor.bean.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author LiuHao
 * @date 2020/9/21 10:10
 * @description MysqlCrudDao 单元测试
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CloudJobExecutorApplication.class)
@SpringBootConfiguration
public class MysqlCurdDaoTest {



    @Autowired
    private ActorDao actorDao;


    @Test
    public void testSelectList(){
        List<Actor> actor = actorDao.selectList("select * from actor", Maps.newHashMap());
        System.out.println(actor);
    }

    @Test
    public void testSelectOne(){
        Map<String,Object> param = Maps.newHashMap();
        param.put("id","1");
        Actor actor = actorDao.selectOne("select * from actor where id=:id", param);
        System.out.println(actor);
    }

    @Test
    public void testAdd(){
        Actor actor = new Actor();
        actor.setId(UUID.randomUUID().toString());
        actor.setName("测试");
        actor.setSex(1);
        actor.setBirthday("1995.11.12");
        actor.setArea("测试");
        actor.setVocational("测试");
        actor.setCreateTime(new Date().getTime());
        actor.setUpdateTime(new Date().getTime());
        actor.setCreatorUser("测试");
        actor.setUpdateUser("测试");
        actor.setStatus(0);
        String result = actorDao.add(actor);
        System.out.println(result);
    }

    //@Transactional(value = "cloudMovieTxManager")//测试事务时开启
    @Test
    public void testAddCollection(){
        Actor actor = new Actor();
        actor.setId(UUID.randomUUID().toString());
        actor.setName("测试");
        actor.setSex(1);
        actor.setBirthday("1995.11.12");
        actor.setArea("测试");
        actor.setVocational("测试");
        actor.setCreateTime(new Date().getTime());
        actor.setUpdateTime(new Date().getTime());
        actor.setCreatorUser("测试");
        actor.setUpdateUser("测试");
        actor.setStatus(0);

        Actor actor1 = new Actor();
        actor1.setId(UUID.randomUUID().toString());
        actor1.setName("测试");
        actor1.setSex(1);
        actor1.setBirthday("1995.11.12");
        actor1.setArea("测试");
        actor1.setVocational("测试");
        actor1.setCreateTime(new Date().getTime());
        actor1.setUpdateTime(new Date().getTime());
        actor1.setCreatorUser("测试");
        actor1.setUpdateUser("测试");
        actor1.setStatus(0);

        List<Actor> actors = Lists.newArrayList(actor,actor1);
        List<String> results = actorDao.add(actors);
        System.out.println(results);
    }

    @Test
    public void testUpdate(){
        Actor actor = new Actor();
        actor.setId("bfe000b2-a071-411a-8a66-5e543794857d");
        actor.setName("测试更新");
        actor.setSex(1);
        actor.setBirthday("1995.11.12");
        actor.setArea("测试");
        actor.setVocational("测试");
        actor.setCreateTime(new Date().getTime());
        actor.setUpdateTime(new Date().getTime());
        actor.setCreatorUser("测试");
        actor.setUpdateUser("测试");
        actor.setStatus(0);

        String result = actorDao.update(actor);
        System.out.println(result);
    }

    @Test
    public void testUpdateCollection(){
        Actor actor = new Actor();
        actor.setId("af3d787d-34f8-465c-bc60-5a60693544c2");
        actor.setName("测试更新af3d787d-34f8-465c-bc60-5a60693544c2");
        actor.setSex(1);
        actor.setBirthday("1995.11.12");
        actor.setArea("测试");
        actor.setVocational("测试");
        actor.setCreateTime(new Date().getTime());
        actor.setUpdateTime(new Date().getTime());
        actor.setCreatorUser("测试");
        actor.setUpdateUser("测试");
        actor.setStatus(0);

        Actor actor1 = new Actor();
        actor1.setId("e4b8ef2a-028c-457f-bab3-b532e0629966");
        actor1.setName("测试更新e4b8ef2a-028c-457f-bab3-b532e0629966");
        actor1.setSex(1);
        actor1.setBirthday("1995.11.12");
        actor1.setArea("测试");
        actor1.setVocational("测试");
        //actor1.setCreateTime(new Date().getTime());
        actor1.setUpdateTime(new Date().getTime());
        actor1.setCreatorUser("测试");
        actor1.setUpdateUser("测试");
        actor1.setStatus(0);


        List<Actor> actors = Lists.newArrayList(actor,actor1);

        List<String> results = actorDao.update(actors);

        System.out.println(results);
    }


    @Test
    public void testFindOne(){
        Actor actor = actorDao.findOne("e4b8ef2a-028c-457f-bab3-b532e0629966");
        System.out.println(actor);
    }

    @Test
    public void testFindAllByIds(){
        List<Actor> actors = actorDao.findAll(Lists.newArrayList("000","1","2"));
        System.out.println(actors);
    }

    @Test
    public void testFindAll(){
        List<Actor> actors = actorDao.findAll();
        System.out.println(actors);
        System.out.println("查询到"+actors.size()+"条记录");
    }


    @Test
    public void testDeleteOne(){
        Actor actor1 = new Actor();
        actor1.setId("e4b8ef2a-028c-457f-bab3-b532e0629966");
        actor1.setName("测试更新e4b8ef2a-028c-457f-bab3-b532e0629966");
        actor1.setSex(1);
        actor1.setBirthday("1995.11.12");
        actor1.setArea("测试");
        actor1.setVocational("测试");
        //actor1.setCreateTime(new Date().getTime());
        actor1.setUpdateTime(new Date().getTime());
        actor1.setCreatorUser("测试");
        actor1.setUpdateUser("测试");
        actor1.setStatus(0);

        long start = System.currentTimeMillis();
        actorDao.delete(actor1);
        System.out.println(System.currentTimeMillis()-start);

        long start1 = System.currentTimeMillis();
        List<Actor> actors = actorDao.findAll();
        System.out.println(System.currentTimeMillis()-start1);
        System.out.println(actors);
        System.out.println("查询到"+actors.size()+"条记录");
    }


    @Test
    public void testDeleteCollection(){
        Actor actor = new Actor();
        actor.setId("af3d787d-34f8-465c-bc60-5a60693544c2");

        Actor actor1 = new Actor();
        actor1.setId("6ea19b98-e441-4bb0-b795-495635d7e610");

        actorDao.delete(Lists.newArrayList(actor,actor1));


        List<Actor> actors = actorDao.findAll();
        System.out.println(actors);
        System.out.println("查询到"+actors.size()+"条记录");
    }

    @Test
    public void testDeleteByid(){
        actorDao.delete("1");
        List<Actor> actors = actorDao.findAll();
        System.out.println(actors);
        System.out.println("查询到"+actors.size()+"条记录");
    }

    @Test
    public void testDeleteByids(){
        actorDao.deleteById(Lists.newArrayList("3","2"));
        List<Actor> actors = actorDao.findAll();
        System.out.println(actors);
        System.out.println("查询到"+actors.size()+"条记录");
    }



















}
