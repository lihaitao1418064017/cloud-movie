package org.hhy.cloud.crawl.dao;

import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.junit.jupiter.api.Test;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/10/31 6:56 PM
 **/
@SpringBootTest
public class TemplatePageDaoTest {

    @Autowired
    private TemplatePageDao dao;

    @Test
    public void test01(){

        TemplatePageVO pageVO = dao.selectTemplatePageVO("def698579f4340d696ce391fd739404f");
        assert pageVO!=null;

        dao.selectPage(QueryParam.build().addTerm(Term.build("job_id","def698579f4340d696ce391fd739404f")));
    }
}
