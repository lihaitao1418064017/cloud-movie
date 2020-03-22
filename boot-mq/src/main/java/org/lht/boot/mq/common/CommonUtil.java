package org.lht.boot.mq.common;

import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

/**
 * @author LiHaitao
 * @description CommonUtil:提供消息各个消息组件的通用方法
 * @date 2020/3/2 16:38
 **/
public class CommonUtil {


    /**
     * 转换消息，填充消息id和时间戳
     *
     * @param data
     * @return
     */
    public static String covertMessage(Object data) {
        if (data instanceof String) {
            return (String) data;
        }
        try {
            String dataStr = JSONObject.toJSONString(data);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", JSONObject.parseObject(dataStr));
            jsonObject.put("messageId", UUID.randomUUID().toString());
            jsonObject.put("timestamp", System.currentTimeMillis());
            return jsonObject.toJSONString();
        } catch (Exception e) {
            return null;
        }
    }
}
