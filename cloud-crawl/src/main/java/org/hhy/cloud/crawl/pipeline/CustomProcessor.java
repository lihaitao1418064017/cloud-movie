package org.hhy.cloud.crawl.pipeline;

import cn.hutool.core.collection.CollectionUtil;
import org.hhy.cloud.crawl.entity.CustomSpider;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 自定义Processor
 * @author: LiHaitao
 * @date: 2020/9/22 11:13
 */
public class CustomProcessor implements PageProcessor {

    private CustomSpider customSpider;

    @Autowired
    private Site site;

    Map<String, Map<String, Object>> map = new HashMap<>();

    @Override
    public void process(Page page) {

        TemplatePageVO templatePageVO = customSpider.getPages();
        //如果是列表页
        if (page.getUrl().regex(templatePageVO.getUrlRegex()).match()) {
            //            map.put(page.get)
        }


        String url = page.getRequest().getUrl();

        List<TemplatePage> pages = null;
        pages.forEach(each -> {

            List<String> listUrl = page
                    .getHtml()
                    .links()
                    .regex(each.getUrlRegex())
                    .all();
            //添加新链接
            page.addTargetRequests(listUrl);
            if (CollectionUtil.isEmpty(map)) {
                listUrl.forEach(u -> {
                    Map<String, Object> values = map.get(url);
                    if (CollectionUtil.isEmpty(values)) {
                        values.put(url, url);
                    }
                });
            } else {

                // TODO: 2020/9/22  
                Map<String, Object> map = this.map.get(url);

                //                List<TemplateField> fields = each.get();
                //                fields.forEach(field -> {
                //                    //put<key：字段名 value：爬取值>
                //                    map.put(field.getName(), page
                //                            .getHtml()
                //                            .xpath(field.getXpathRule()));
                //                });

                page.putField(url, map);
            }
        });
    }


    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 初始化爬虫基础配置
     *
     * @param customSpider 自定义爬虫信息
     */
    public void setCustomSpider(CustomSpider customSpider) {
        this.customSpider = customSpider;
        this.getSite()
                .setSleepTime(customSpider.getSleepTime() == null ? 5000 : customSpider.getSleepTime() * 1000)
                .setRetryTimes(customSpider.getRetryTimes() == null ? 0 : customSpider.getRetryTimes())
                .setRetrySleepTime(customSpider.getRetrySleepTime() == null ? 1000 : customSpider.getRetrySleepTime())
                .setCycleRetryTimes(customSpider.getCycleRetryTimes() == null ? 0 : customSpider.getCycleRetryTimes())
                .setTimeOut(customSpider.getTimeOut() == null ? 5000 : customSpider.getTimeOut() * 1000);
    }
}