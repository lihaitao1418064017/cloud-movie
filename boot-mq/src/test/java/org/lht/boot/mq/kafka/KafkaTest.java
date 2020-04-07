package org.lht.boot.mq.kafka;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.lht.boot.mq.kafka.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiHaitao
 * @description KafkaTest:
 * @date 2020/1/16 14:16
 **/
@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaSender<String, String> kafkaSender;

    @Test
    void sendTest() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("longitude", 120.12312312);
        jsonObject.put("latitude", 30.000000);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("00000000000000654321");
        jsonObject.put("channelIds", jsonArray);

        long i = 0;
        //        for (; ; ) {
        //            i++;
        //        kafkaSender.sendByJsonStr("topic-gps", jsonObject).addCallback(new KafkaListenableFutureCallback<>());
        //            if (i == 100000) {
        //                break;
        //            }
        //        }

        String caseEvent = "{\n" +
                "        \"wfsj\": 1585792810000,\n" +
                "        \"wfdz\": \"友谊路春雷路口\",\n" +
                "        \"caseEventId\": \"3202051531230652\",\n" +
                "        \"caseEventSign\": \"violation\",\n" +
                "        \"caseEventTime\": 1585792810000,\n" +
                "        \"caseEventType\": \"1\",\n" +
                "        \"updateTime\": 1585792810000,\n" +
                "        \"id\": \"320205153123065violation\",\n" +
                "        \"hphm\": \"苏E131234\",\n" +
                "        \"deptCode\": \"2635\",\n" +
                "        \"personCode\": \"KD015347\",\n" +
                "        \"status\": 2\n" +
                "}";


        kafkaSender.send("caseEventData", caseEvent);

    }
}
