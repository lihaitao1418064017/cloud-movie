package org.lht.boot.security.client.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description: OAuth2请求工具类
 * @author: LiHaitao
 * @date: 2020/4/30 14:22
 */
public class OAuth2RequestUtil {


    /**
     * base64编码客户端id和客户端密钥
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    public static String encodeCredentials(String clientId, String clientSecret) {
        String credentials = clientId + ":" + clientSecret;
        return new String(Base64Utils.encode(credentials.getBytes()));
    }


    /**
     * 获取授权码accessToken的参数体
     *
     * @param authorizationCode
     * @return
     */
    public static MultiValueMap<String, String> getAccessTokenBody(String authorizationCode, String scope, String redirect_uri, String grant_type) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grant_type);
        formData.add("scope", scope);
        formData.add("code", authorizationCode);
        formData.add("redirect_uri", redirect_uri);
        return formData;
    }

    public static HttpHeaders getBasicHeader(String clientAuthentication) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Basic " + clientAuthentication);

        return httpHeaders;
    }


    public static String buildAuthorizeUrl(String endpoint, Map<String, String> parameters) {
        List<String> paramList = new ArrayList<>(parameters.size());
        parameters.forEach((name, value) -> {
            paramList.add(name + "=" + value);
        });
        return endpoint +
                "?" +
                paramList
                        .stream()
                        .reduce((a, b) -> a + "&" + b)
                        .get();
    }


    public static String getEncodedUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
