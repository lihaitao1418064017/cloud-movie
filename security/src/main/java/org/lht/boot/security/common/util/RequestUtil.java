package org.lht.boot.security.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiHaitao
 * @description RequestUtil:
 * @date 2020/3/23 16:55
 **/
public class RequestUtil {

    /**
     * 判断是否为 AJAX 请求
     *
     * @param request HttpServletRequest
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
