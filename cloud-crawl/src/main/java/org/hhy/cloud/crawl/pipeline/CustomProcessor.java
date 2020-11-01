package org.hhy.cloud.crawl.pipeline;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.hhy.cloud.crawl.entity.CustomSpider;
import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.vo.TemplateFieldVO;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hhy.cloud.crawl.SinaBlogProcessor.URL_LIST;
import static org.hhy.cloud.crawl.SinaBlogProcessor.URL_POST;

/**
 * @description: 自定义Processor
 * @author: LiHaitao
 * @date: 2020/9/22 11:13
 */
@Component
@Slf4j
public class CustomProcessor implements PageProcessor {

    private CustomSpider customSpider;

    @Autowired
    private Site site;


    @Override
    public void process(Page page) {

        TemplatePageVO templatePageVO = customSpider.getPages();

        //判断页面规则
        String urlRegex = templatePageVO.getUrlRegex();


        //列表页
        if (page.getUrl().regex(urlRegex).match()) {

            page.addTargetRequests(page.getHtml().xpath(templatePageVO.getKeyXpath()).links().regex(templatePageVO.getKeyRegex()).all());

            //获取列表页的各个字段的解析规则等信息
            List<TemplateFieldVO> listFields = templatePageVO.getListFields();

            //列表页列表长度
            int size = page.getHtml().xpath(templatePageVO.getKeyXpath()).links().all().size();

            for (int i = 0; i < size; i++) {

                Map<String, Object> valueMap = new LinkedHashMap<>();

                //获取列表和详情页的关联属性，即为map的key
                String key = page.getHtml().xpath(templatePageVO.getKeyXpath()).links().regex(templatePageVO.getKeyRegex()).nodes().get(i).get();

                //将列表页需要的各个字段信息记录
                for (TemplateFieldVO listField : listFields) {
                    String value = page.getHtml().xpath(listField.getXpathRule()).links().regex(listField.getRegex()).nodes().get(i).get();
                    valueMap.put(listField.getName(), value);
                }
                //将列表页数据放入page，key为列表页和详情页的关联值，value为获取到的所有信息字段
                page.putField(key, valueMap);
            }

            log.info("*****************爬虫列表结束******************");


            //文章页
        } else {
            List<TemplateFieldVO> detailFields = templatePageVO.getDetailFields();
            String key = page.getRequest().getUrl();
            Map<String, Object> valueMap = new LinkedHashMap<>();
            detailFields.forEach(detailField -> {
                Selectable selectable = page.getHtml().xpath(detailField.getXpathRule());
                if (StrUtil.isNotBlank(detailField.getRegex())) {
                    selectable=selectable.regex(detailField.getRegex());
                }
                //将详情页属性put进去
                valueMap.put(detailField.getName(), selectable.get());
            });
            //根据 列表页的key放入page
            page.putField(key, valueMap);

            log.info("*****************爬虫详情结束******************");

        }


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