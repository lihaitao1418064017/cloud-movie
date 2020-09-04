package org.lht.boot.security.server.domain.vo;

import lombok.Data;
import org.lht.boot.security.server.domain.entity.Project;
import org.lht.boot.web.domain.vo.AbstractVO;

/**
 * @author LiHaitao
 * @description 项目
 * @date 2020/7/14 17:31
 **/
@Data
public class ProjectVO extends AbstractVO<Project, Integer> {


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
