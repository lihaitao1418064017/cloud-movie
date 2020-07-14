package org.lht.boot.security.server.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.lht.boot.web.domain.entity.BaseCrudEntity;

/**
 * @author LiHaitao
 * @description 项目管理
 * @date 2020/7/14 16:46
 **/
@Data
@TableName("user")
public class Project extends BaseCrudEntity<Integer> {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String clientId;

    private String clientSecret;

    private String scope;

    /**
     * 授权模式，用逗号隔开
     */
    private String authorizedGrantTypes;

    /**
     * 重定向地址
     */
    private String webServerRedirectUri;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String autoapprove;
}
