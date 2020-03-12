package org.lht.boot.web.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * @author LiHaitao
 * @description MetaHandler: 公共字段填充
 * @date 2020/3/12 16:15
 **/
public class MetaHandler implements MetaObjectHandler {


    @Override
    public void insertFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateCode = getFieldValByName("updateCode", metaObject);
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createCode = getFieldValByName("createCode", metaObject);
        Object status = getFieldValByName("status", metaObject);

        if (updateTime == null) {
            setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        }
        if (updateCode == null) {
            setFieldValByName("updateId", "ll", metaObject);
        }
        if (createCode == null) {
            setFieldValByName("createCode", "1111", metaObject);
        }
        if (createTime == null) {
            setFieldValByName("createTime", System.currentTimeMillis(), metaObject);
        }
        if (status == null) {
            setFieldValByName("status", 1, metaObject);
        }
    }

    @Override
    public void updateFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateCode = getFieldValByName("updateCode", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        }
        if (updateCode == null) {
            setFieldValByName("updateId", "ll", metaObject);
        }
    }


}
