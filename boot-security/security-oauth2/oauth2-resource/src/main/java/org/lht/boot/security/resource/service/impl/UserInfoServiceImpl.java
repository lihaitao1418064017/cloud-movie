package org.lht.boot.security.resource.service.impl;

import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.security.resource.dao.UserInfoDao;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.service.UserInfoService;
import org.lht.boot.security.resource.vo.UserRegisterVO;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description UserInfoServiceImpl:
 * @date 2020/3/19 14:50
 **/
@Service
public class UserInfoServiceImpl extends BaseMybatisCrudServiceImpl<UserInfo, Integer, UserInfoDao> implements UserInfoService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public Integer register(UserRegisterVO vo) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(vo, userInfo);
        userInfo.setPassword(passwordEncoder.encode(vo.getPassword()));
        return this.insert(userInfo);
    }
}
