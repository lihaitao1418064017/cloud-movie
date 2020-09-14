package org.lht.boot.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.api.param.QueryParam;
import org.lht.boot.web.api.param.util.ParamMybatisUtil;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description BaseMybatisPlusDao:mybatis的基础dao
 * @date 2019/12/13 16:42
 **/
public interface BaseMybatisPlusDao<E extends BaseCrudEntity> extends BaseMapper<E> {


    /**
     * 分页查询
     *
     * @param queryParam
     * @return 分页结果
     */
    default PagerResult<E> selectPage(QueryParam queryParam) {
        PagerResult<E> page = ParamMybatisUtil.buildPage(queryParam);
        IPage<E> iPage = selectPage(page, ParamMybatisUtil.toQueryWrapper(queryParam));
        page.setTotalPages(iPage.getPages());
        page.setTotal(iPage.getTotal());
        page.setRecords(iPage.getRecords());
        return page;
    }


    /**
     * 删除
     *
     * @param param 查询参数
     * @param <Q>   泛型继承
     * @return 删除数量
     */
    default <Q extends Param> int delete(Q param) {
        return delete(ParamMybatisUtil.toQueryWrapper(param));
    }


}
