package org.lht.boot.security.core.param;

import lombok.Data;
import org.lht.boot.web.api.param.QueryParam;

/**
 * @author LiHaitao
 * @description ClientQueryParam:
 * @date 2020/7/3 11:29
 **/
@Data
public class ClientQueryParam extends QueryParam {

    private String clientId;

    private String clientSecret;

}
