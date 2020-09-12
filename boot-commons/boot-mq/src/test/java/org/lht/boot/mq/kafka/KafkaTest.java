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
                "        \"bjdh\": \"12312312\",\n" +
                "        \"caseEventId\": \"12312398765356\",\n" +
                "        \"caseEventSign\": \"policecase\",\n" +
                "        \"caseEventType\": \"970109\",\n" +
                "        \"sfdd\": \"西安\",        \n" +
                "        \"bjrxm\": \"www\",\n" +
                "        \"jjyxm\": \"www\",\n" +
                "        \"updateTime\": 1586245638000,\n" +
                "        \"caseEventContent\": \"test警情类型\",\n" +
                "        \"countTime\": 1586245638000,\n" +
                "        \"personCode\": \"KD015347\",\n" +
                "        \"bjsj\": 1586245638000,\n" +
                "        \"related\": \"relating\",\n" +
                "        \"caseEventTime\": 1586247966000,\n" +
                "        \"deptCode\": \"2635\",\n" +
                "        \"status\": 0\n" +
                "}";


        kafkaSender.send("cvf_caseEventData", caseEvent);

    }


    @Test
    public void test01() {
        String topic = "order";

        kafkaSender.send(topic, "nihao");
    }
}
