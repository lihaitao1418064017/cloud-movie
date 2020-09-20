package org.hhy.cloud.crawl.config;

import us.codecraft.webmagic.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SpiderHolder
 * @Description TODO
 * @Date 2020/9/20 12:50 AM
 * @Created by yupeng
 */
public class SpiderHolder {



    //正在运行的爬虫对象的容器
    private static Map<String/*jobId*/, List<Spider>> spiderContainer = new ConcurrentHashMap<>();

    //放入爬虫
    public static void putSpider(Spider... spiders) {
        for (Spider spider : spiders) {
            List<Spider> spiderList = spiderContainer.computeIfAbsent(spider.getUUID(), k -> new ArrayList<>());
            spiderList.add(spider);
        }
    }

    //获取爬虫
    public static List<Spider> getSpider(String uuid) {
        return spiderContainer.get(uuid);
    }

    //删除爬虫
    public static void removeSpider(String uuid) {
        spiderContainer.remove(uuid);
    }
}
