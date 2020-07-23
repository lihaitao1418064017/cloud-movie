package org.lht.boot.cloud.admin;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import org.junit.jupiter.api.Test;
import org.lht.boot.cloud.admin.util.DingDingMessageUtil;
import org.lht.boot.cloud.admin.util.DingTalkAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description
 * @date 2020/7/23 15:40
 **/
@SpringBootTest
public class DingTalkUtil {

    @Autowired
    private DingTalkAccessTokenUtil dingTalkAccessTokenUtil;


    @Test
    public void test01() throws ApiException {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingkgouhnkykoplhzqu");
        request.setAppsecret("xBgaMX_8dmXp2TuJcuDJNJdq17WRkmHAKhV4Tgz1PwmWBhO-_xSKmc5A7VKrquxt");
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        System.out.println(response.getAccessToken());
    }

    @Test
    public void test02() {
        String accessToken = dingTalkAccessTokenUtil.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    public void test03() {
        try {
            DingDingMessageUtil.sendTextMessage("服务挂啦!", Lists.newArrayList(), dingTalkAccessTokenUtil.getAccessToken());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void send() throws ApiException {


        String markMessage = "<html>\n" +
                "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </haed>\n" +
                "         <body>\n" +
                "         <style>table{font-size:14px;}</style> \n" +
                "         <div align=\"center\">\n" +
                "         <font color=\"#525252\">\n" +
                "           <table border=\"0\" style=\"border:5px solid #F2F2F2;\" cellspacing=\"2\" cellpadding=\"2\" width=\"900\" style=\"table-layout:fixed\">\n" +
                "         <tr bgcolor=\"#D1D1D1\"> \n" +
                "         <th align=\"left\" style=\"font-size:23px;\">告警信息</marquee></th> \n" +
                "         </tr>  <tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警项名: {ITEM.NAME1}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\"><b>告警主机:</b>{HOST.NAME1}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警IP: {HOST.IP1}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警项Key值: {ITEM.KEY1}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警触发规则:{TRIGGER.EXPRESSION}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警时间:{EVENT.DATE} {EVENT.TIME}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警等级:{TRIGGER.SEVERITY}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">告警值: {ITEM.VALUE1}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">最新采集值: {{HOSTNAME}:{TRIGGER.KEY}.last(0)}\n" +
                "</td>  </tr>\n" +
                "<tr><td align=\"left\" style=\"font-family:微软雅黑; size=5\" style=\"WORD-WRAP:break-word\">问题描述:{TRIGGER.DESCRIPTION}              \n" +
                "\n" +
                "</td>  </tr>\n" +
                "\n" +
                "                 </table> \n" +
                "                 \n" +
                "\n" +
                "                 <p>此邮件为监控平台自动发送，请勿回复!</p> \n" +
                "                 \n" +
                "                 </body> \n" +
                "                 </html> ";
        DingDingMessageUtil.sendLinkMessage("告警", markMessage, "www.baidu.com", "28d23332467733508e6f550e40d43cb8147f2f9b7a613bb7f0f552c3cfb93513");
    }
}
