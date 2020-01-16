package org.lht.boot.web.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.elasticsearch.script.Script;
import org.lht.boot.web.common.exception.JestException;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Description: Es工具类
 * @Author: Lihaitao
 * @Date: 2020/1/14 16:22
 * @UpdateUser:
 * @UpdateRemark:
 */
@Slf4j
public class JestUtil {


    public static <T extends JestResult> T execute(JestClient jestClient, Gson gson, Action<T> action) {
        Validate.notNull(jestClient, "jest client not found");
        T jestResult;
        try {
            if (log.isInfoEnabled()) {
                log.info("es request url:{},requestBody:{}", action.toString(), action.getData(gson));
            }
            jestResult = jestClient.execute(action);
        } catch (IOException e) {
            throw new JestException(String.format("dao.es.%s.error", StringUtils.lowerCase(action.getClass().getSimpleName())));
        }
        if (jestResult == null) {
            throw new JestException("result is null");
        } else if (!jestResult.isSucceeded()) {
            throw new JestException(jestResult.getErrorMessage());
        } else {
            return jestResult;
        }
    }

    public static String buildScript(Script script) {
        String payload;
        try {
            payload = jsonBuilder()
                    .startObject()
                    .startObject("script")
                    .field("lang", script.getLang())
                    .field("inline", script.getIdOrCode())
                    .endObject()
                    .endObject()
                    .string();
        } catch (IOException e) {
            throw new JestException("build Script error", e);
        }
        return payload;
    }


    public static String buildScript(Map<String, Object> data, boolean completable) {
        List<String> scripts = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(data)) {
            Iterator var3 = data.entrySet().iterator();
            while (var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) var3.next();
                Object value = entry.getValue();
                if (value == null) {
                    if (completable) {
                        scripts.add("ctx._source." + (String) entry.getKey() + "=null");
                    }
                } else {
                    Class vClass = value.getClass();
                    if (vClass.isAssignableFrom(Date.class)) {
                        value = ((Date) value).getTime();
                    }

                    if (value instanceof String) {
                        scripts.add("ctx._source." + (String) entry.getKey() + "='" + value + "'");
                    } else if (value instanceof Long) {
                        scripts.add("ctx._source." + (String) entry.getKey() + "=" + value + "L");
                    } else {
                        scripts.add("ctx._source." + (String) entry.getKey() + "=" + JSONObject.toJSONString(value));
                    }
                }
            }
        }
        return Joiner.on(";").join(scripts);
    }


}
