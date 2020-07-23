package org.lht.boot.cloud.admin.util;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import java.util.List;

/**
 * 钉钉机器人消息发送工具类
 */
public class DingDingMessageUtil {

    /**
     * 发送文本格式消息
     *
     * @param msg
     * @param phones
     * @param accessToken
     * @return
     * @throws ApiException
     */
    public static OapiRobotSendResponse sendTextMessage(String msg, List<String> phones, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + accessToken);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(msg);
        request.setText(text);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(phones);
        // isAtAll类型如果不为Boolean，请升级至最新SDK
        at.setIsAtAll(true);
        request.setAt(at);
        return client.execute(request);
    }

    /**
     * 发送链接格式消息
     *
     * @param title
     * @param text
     * @param messageUrl
     * @param accessToken
     * @return
     * @throws ApiException
     */
    public static OapiRobotSendResponse sendLinkMessage(String title, String text, String messageUrl, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + accessToken);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(messageUrl);
        link.setPicUrl("");
        link.setTitle(title);
        link.setText(text);
        request.setLink(link);
        return client.execute(request);
    }

    /**
     * 发送markDown格式消息
     *
     * @param title
     * @param text        #### 杭州天气 @156xxxx8827\n" +
     *                    "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
     *                    "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
     *                    "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
     * @param accessToken
     * @return
     * @throws ApiException
     */

    public static OapiRobotSendResponse sendMarkDownMessage(String title, String text, String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + accessToken);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(text);
        request.setMarkdown(markdown);
        return client.execute(request);
    }


}
