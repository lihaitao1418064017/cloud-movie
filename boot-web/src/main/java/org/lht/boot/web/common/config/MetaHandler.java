package org.lht.boot.web.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author LiHaitao
 * @description MetaHandler: 公共字段填充
 * @date 2020/3/12 16:15
 **/
@Configuration
public class MetaHandler implements MetaObjectHandler {


    @Override
    public void insertFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        //获取当前用户
        Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        String name = null;
        if (auth.isPresent()) {
            Authentication authentication = auth.get();
            UserDetails userDetails = (UserDetails) auth.get();
            name = authentication.getName();
        }
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createCode = getFieldValByName("creatorUser", metaObject);
        Object status = getFieldValByName("status", metaObject);


        if (createCode == null) {
            setFieldValByName("creatorUser", name, metaObject);
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
        //获取当前用户
        Optional<Authentication> auth = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        String name = null;
        if (auth.isPresent()) {
            Authentication authentication = auth.get();
            name = authentication.getName();
        }
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateCode = getFieldValByName("updateUser", metaObject);
        if (updateTime == null) {
            setFieldValByName("updateTime", System.currentTimeMillis(), metaObject);
        }
        if (updateCode == null) {
            setFieldValByName("updateUser", name, metaObject);
        }
    }


}
