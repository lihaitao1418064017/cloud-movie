package org.lht.boot.security.core.param;

import lombok.Data;
import org.lht.boot.web.api.param.QueryParam;

/**
 * @author LiHaitao
 * @description UserQueryParam:用户权限查询param
 * @date 2020/7/3 11:28
 **/
@Data
public class UserQueryParam extends QueryParam {

    private String clientId;

    private String clientSecret;

    private String accessToken;
}
