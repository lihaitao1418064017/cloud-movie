package org.lht.boot.security.server.common.util;

import com.alibaba.fastjson.JSONObject;
import org.lht.boot.lang.util.RestTemplateUtil;
import org.springframework.http.HttpMethod;

/**
 * @author LiHaitao
 * @description
 * @date 2020/9/3 17:04
 **/
public class LoginAddressUtil {

    /**
     * 通过IP获取城市地址
     *
     * @param ip
     * @return
     */
    public static String getCity(String ip) {
        return getAddressInfo(ip).getString("city");

    }

    /**
     * 通过IP获取省份地址
     *
     * @param ip
     * @return
     */
    public static String getProvince(String ip) {
        return getAddressInfo(ip).getString("pro");
    }


    /**
     * 通过IP获取地址信息
     *
     * @param ip
     * @return
     */
    public static JSONObject getAddressInfo(String ip) {
        String result = RestTemplateUtil.exchangeHandle("http://whois.pconline.com.cn/ipJson.jsp?ip=223.104.145.125&json=true", HttpMethod.GET, String.class);
        return JSONObject.parseObject(result);
    }

}
