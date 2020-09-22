package org.hhy.cloud.crawl.pipeline;

import com.alibaba.fastjson.JSONObject;
import org.hhy.cloud.crawl.constant.CrawlConstant;
import org.lht.boot.mq.kafka.producer.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public void process(ResultItems resultItems, Task task) {


        String requestUrl = resultItems.getRequest().getUrl();
        Map<String, Object> resultMap = resultItems.getAll();
        //合并数据
        LinkedHashMap<String, String> item = itemHolder.get(requestUrl);
        //把暂存的数据删了 防止内存溢出
        itemHolder.remove(requestUrl);

        resultMap.forEach((k, v) -> item.put(k, ((List) v).get(0).toString()));
        //保存
        final List<LinkedHashMap<String, String>> resultList = new ArrayList<>();
        resultList.add(item);

        // 保存数据


        String content = JSONObject.toJSONString(resultItems.getAll());
        kafkaSender.send(CrawlConstant.CRAWL_TOPIC, content);
    }
}
