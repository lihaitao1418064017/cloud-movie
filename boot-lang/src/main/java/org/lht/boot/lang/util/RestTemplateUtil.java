package org.lht.boot.lang.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description: Http请求工具类
 * @Author: Lihaitao
 * @Date: 2020/1/13 17:02
 * @UpdateUser:
 * @UpdateRemark:
 */
@Slf4j
public class RestTemplateUtil {

    /**
     * 延迟加载，当被调用时才初始化，保证线程安全
     */
    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplateBuilder().additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8"))).build();
    }

    private RestTemplateUtil() {
    }

    /**
     * 单例实例
     */
    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * 创建HttpEntity
     *
     * @param body
     * @param <T>
     * @return
     */
    private static <T> HttpEntity createHttpEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (null != body) {
            if (body instanceof String) {
                return new HttpEntity<>(body, headers);
            } else {
                return new HttpEntity<>(JSON.toJSONString(body), headers);
            }
        } else {
            return new HttpEntity<>(headers);
        }
    }

    /**
     * 创建HttpEntity
     *
     * @param body
     * @param <T>
     * @return
     */
    private static <T> HttpEntity createHttpEntity(T body,HttpHeaders httpHeaders) {
        if (null != body) {
            if (body instanceof String) {
                return new HttpEntity<>(body, httpHeaders);
            } else {
                return new HttpEntity<>(JSON.toJSONString(body), httpHeaders);
            }
        } else {
            return new HttpEntity<>(httpHeaders);
        }
    }

    /**
     * http请求
     *
     * @param url
     * @param method
     * @param body
     * @param responseType
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> S exchangeHandle(String url, HttpMethod method, T body, Class<S> responseType) {
        log.info("---------请求的服务器地址:---------{}", method + ": " + url);
        log.info("---------请求的Body:---------{}", body != null ? JSON.toJSONString(body) : null);
        String response = RestTemplateUtil.getInstance().exchange(url, method, RestTemplateUtil.createHttpEntity(body), String.class).getBody();
        log.info("---------返回的Response:---------{}", response);
        return JSON.parseObject(response, responseType);
    }

    /**
     * http请求
     *
     * @param url
     * @param method
     * @param responseType
     * @param <S>
     * @return
     */
    public static <S> S exchangeHandle(String url, HttpMethod method, Class<S> responseType) {
        log.info("---------请求的服务器地址:---------{}", method + ": " + url);
        log.info("---------请求的Body:---------{}", "null");
        String response = RestTemplateUtil.getInstance().exchange(url, method, RestTemplateUtil.createHttpEntity(null), String.class).getBody();
        log.info("---------返回的Response:---------{}", response);
        return JSON.parseObject(response, responseType);
    }


    public static <S, T> S exchangeHandleBasicHeader(String url, HttpHeaders httpHeaders, T body, HttpMethod method, Class<S> responseType) {
        log.info("---------请求的服务器地址:---------{}", method + ": " + url);
        log.info("---------请求的Body:---------{}", body);
        String response = RestTemplateUtil.getInstance().exchange(url, method, createHttpEntity(body, httpHeaders), String.class).getBody();
        log.info("---------返回的Response:---------{}", response);
        return JSON.parseObject(response, responseType);
    }

    /**
     * http请求
     *
     * @param url
     * @param id
     * @param method
     * @param responseType
     * @param <S>
     * @return
     */
    public static <S, T> S exchangeHandle(String url, String id, HttpMethod method, T body, Class<S> responseType) {
        log.info("---------请求的服务器地址:---------{}", method + ": " + url.replace("{ID}", id));
        log.info("---------请求的Body:---------{}", "null");
        String response;
        if (StringUtils.isNotBlank(id)) {
            response = RestTemplateUtil.getInstance().exchange(url, method, RestTemplateUtil.createHttpEntity(body), String.class, id).getBody();
        } else {
            response = RestTemplateUtil.getInstance().exchange(url, method, RestTemplateUtil.createHttpEntity(body), String.class).getBody();
        }
        log.info("---------返回的Response:---------{}", JSON.toJSONString(response));
        return JSON.parseObject(response, responseType);
    }
}
