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
 * @description AbstractMapper:
 * @date 2019/12/13 16:42
 **/
public interface BaseMybatisPlusDao<E extends BaseCrudEntity> extends BaseMapper<E> {


    /**
     * 分页查询
     *
     * @param queryParam
     * @return
     */
    default PagerResult<E> selectPage(QueryParam queryParam) {
        PagerResult<E> page = new PagerResult<E>();
        page.setCurrent(queryParam.getPageNo());
        page.setSize(queryParam.getPageSize());
        IPage<E> iPage = selectPage(page, ParamMybatisUtil.toQueryWrapper(queryParam));
        page.setTotalPages(iPage.getPages());
        page.setTotal(iPage.getTotal());
        page.setRecords(iPage.getRecords());
        return page;
    }


    default <Q extends Param> int delete(Q param) {
        return delete(ParamMybatisUtil.toQueryWrapper(param));
    }


}
