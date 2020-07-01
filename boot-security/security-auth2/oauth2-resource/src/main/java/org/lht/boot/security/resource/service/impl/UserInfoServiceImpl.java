package org.lht.boot.security.resource.service.impl;

import org.lht.boot.security.resource.dao.UserInfoDao;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserInfoServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class UserInfoServiceImpl extends BaseMybatisCrudServiceImpl<UserInfo, Integer, UserInfoDao> implements UserInfoService {

    @Override
    public UserInfo selectByUsername(String username) {
        return selectSingle(QueryParam.build(Term.build("username", username)));
    }

    @Override
    public PagerResult<UserVO> page(QueryParam queryParam) {
        PagerResult<UserVO> page = new PagerResult<UserVO>();
        page.setPage(queryParam);
        page = dao.page(page, queryParam.getTerms());
        page.totalPages();
        return page;
    }
}
