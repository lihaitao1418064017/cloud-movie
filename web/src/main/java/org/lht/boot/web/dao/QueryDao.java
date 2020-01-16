package org.lht.boot.web.dao;

import org.lht.boot.web.api.param.PagerResult;
import org.lht.boot.web.api.param.Param;
import org.lht.boot.web.domain.entity.CrudEntity;

import java.util.Collection;
import java.util.List;

/**
 * @author LiHaitao
 * @description QueryDao:查询操作
 * @date 2020/1/2 15:40
 **/
public interface QueryDao<E extends CrudEntity, PK> extends Dao {

    /**
     * 根据id查询
     *
     * @param pk
     * @return
     */
    E findOne(PK pk);

    /**
     * 根据id查询
     *
     * @param ids
     * @return
     */
    List<E> findAll(Collection<PK> ids);


    /**
     * 查询全部
     *
     * @return
     */
    List<E> findAll();

    /**
     * 查询总数
     *
     * @return
     */
    <Q extends Param> Long count(Q param);


    /**
     * 根据条件查询
     *
     * @param param
     * @return
     */
    <Q extends Param> PagerResult<E> selectPage(Q param);

    /**
     * 条件查询
     *
     * @param param
     * @return
     */
    <Q extends Param> List<E> select(Q param);

    /**
     * 查询一个
     *
     * @param param
     * @return
     */
    default <Q extends Param> E selectSingle(Q param) {
        List<E> list = select(param);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


}
