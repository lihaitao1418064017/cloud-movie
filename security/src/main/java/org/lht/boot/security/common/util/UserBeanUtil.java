package org.lht.boot.security.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

public class UserBeanUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    //    /**
    //     * 组装登录的userbean信息
    //     *
    //     * @param request
    //     * @return UserBean
    //     */
    //    public static UserBean createLoginUserBean(HttpServletRequest request) {
    //        UserBean authenticationBean = null;
    //        //attempt Authentication when Content-Type is json
    //
    //        if (isJsonRequest(request)) {
    //            try (InputStream is = request.getInputStream()) {
    //                // jackson序列化
    //                authenticationBean = mapper.readValue(is, UserBean.class);
    //            } catch (IOException e) {
    //                throw new ValidationException("参数错误: " + e.getMessage());
    //            }
    //        }
    //        // 非json请求默认为form表单提交
    //        else {
    //            String username = request.getParameter(UserBean.USERNAME);
    //            String password = request.getParameter(UserBean.PASSWORD);
    //            String captcha = request.getParameter(UserBean.CAPTCHA);
    //            String accessToken = request.getParameter(UserBean.ACCESS_TOKEN);
    //            String departmentId = request.getParameter(UserBean.DEPARTMENT_CODE);
    //
    //            authenticationBean = new UserBean(username, password, captcha, accessToken, departmentId);
    //        }
    //        return authenticationBean;
    //    }


    /**
     * 判断是否是json请求
     *
     * @param request
     * @return
     */
    public static boolean isJsonRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (StringUtils.isNotBlank(contentType)) {
            if ((MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(contentType)
                    || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType))) {
                //获取请求方法
                String method = request.getMethod();
                if (HttpMethod.POST.name().equalsIgnoreCase(method)) {
                    return true;
                }
            }
        }

        return false;
    }

}
