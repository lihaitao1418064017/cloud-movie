package org.lht.boot.web.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaitao
 * @description MetaHandler: 公共字段填充
 * @date 2020/3/12 16:15
 **/
@Configuration
public class MetaHandler implements MetaObjectHandler {

    // TODO: 2020/3/18 此处creatorCode和updaterCode通过权限去添加

    @Override
    public void insertFill(org.apache.ibatis.reflection.MetaObject metaObject) {

        Object createTime = getFieldValByName("createTime", metaObject);
        Object createCode = getFieldValByName("creatorId", metaObject);
        Object status = getFieldValByName("status", metaObject);


        if (createCode == null) {
            setFieldValByName("creatorId", "1111", metaObject);
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
        Object updateCode = getFieldValByName("updateId", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        }
        if (updateCode == null) {
            setFieldValByName("updateId", "ll", metaObject);
        }
    }


}
