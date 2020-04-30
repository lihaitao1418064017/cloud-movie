package org.lht.boot.security.client.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

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
     * 获取accessToken参数体
     *
     * @param gruntType
     * @param scope
     * @param code
     * @param redirectUri
     * @return
     */
    public static MultiValueMap<String, String> getAccessTokenBody(String gruntType, String scope, String code, String redirectUri) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", gruntType);
        formData.add("scope", scope);
        formData.add("code", code);
        formData.add("redirect_uri", redirectUri);
        return formData;
    }

    /**
     * 获取Authorization 值为Basic的headers
     *
     * @param clientAuthentication
     * @return
     */
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
                Optional.of(paramList
                        .stream()
                        .reduce((a, b) -> a + "&" + b)
                        .get());
    }


    public static String getEncodedUrl(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
