package org.lht.boot.security.resource.dao;

import org.apache.ibatis.annotations.Param;
import org.lht.boot.security.resource.entity.UserInfo;
import org.lht.boot.security.resource.vo.UserVO;
import org.lht.boot.web.api.param.Term;
import org.lht.boot.web.dao.BaseMybatisPlusDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiHaitao
 * @description UserInfoDao:
 * @date 2020/3/19 13:50
 **/
@Repository
public interface UserInfoDao extends BaseMybatisPlusDao<UserInfo> {

    /**
     * 分页查询，带角色信息
     *
     * @param  start=current*size;
     * @return
     */
    List<UserVO> page(@Param("terms") List<Term> terms, @Param("start") Long start,@Param("size")Long size);
}
