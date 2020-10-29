package org.hhy.cloud.crawl.pipeline;

import com.alibaba.fastjson.JSONObject;
import org.hhy.cloud.crawl.constant.CrawlConstant;
import org.lht.boot.mq.kafka.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiHaitao
 * @description 自定义pipeline，处理爬取内容
 * @date 2020/9/22 9:53
 **/
@Component
public class CustomPipeline implements Pipeline {


    @Autowired
    private KafkaSender<String, String> kafkaSender;

    Map<String, LinkedHashMap<String, String>> itemHolder = new ConcurrentHashMap<>();

    //todo redis实现

    @Override
    public void process(ResultItems resultItems, Task task) {

        Map<String, Object> valueMap = resultItems.getAll();
        valueMap.forEach((k, v) -> {
            //如果不等于null则正常情况下是详情页，找到列表页的数据，将详情页数据加上
            if (itemHolder.get(k) != null) {
                //获取列表页数据
                LinkedHashMap<String, String> map = itemHolder.get(k);
                //加上详情页数据
                map.putAll((LinkedHashMap)v);
                String data = JSONObject.toJSONString(map);
                kafkaSender.send(CrawlConstant.CRAWL_TOPIC, data);
                itemHolder.remove(k);
            } else {
                //列表页直接put进去
                itemHolder.put(k, (LinkedHashMap<String, String>) v);
            }
        });


    }
}
