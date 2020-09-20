package org.hhy.cloud.crawl.pipeline;

import org.hhy.cloud.crawl.entity.MySpider;
import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.entity.TemplatePage;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * 2019/8/27 22:58
 * yechangjun
 */
public class ListPatternProcessor implements PageProcessor {

    private MySpider spider;

    private Site site = Site
            .me()
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        List<TemplatePage> pages = spider.getPages();
        pages.forEach(each -> {
            page.addTargetRequests(page.getHtml().links().regex(each.getUrlRegex()).all());
            List<TemplateField> fields = each.getFields();
            fields.forEach(field -> {
                page.putField(field.getName(),page.getHtml().xpath(field.getXpathRule()));
            });
        });
    }


    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 初始化爬虫基础配置
     * @param mySpider
     */
    public void setmySpider(MySpider mySpider) {
        this.spider = mySpider;
        getSite().setSleepTime(mySpider.getSleepTime() == null ? 5000 : mySpider.getSleepTime() * 1000)
                 .setRetryTimes(mySpider.getRetryTimes() == null ? 0 : mySpider.getRetryTimes())
                 .setRetrySleepTime(mySpider.getRetrySleepTime() == null ? 1000 : mySpider.getRetrySleepTime())
                 .setCycleRetryTimes(mySpider.getCycleRetryTimes() == null ? 0 : mySpider.getCycleRetryTimes())
                 .setTimeOut(mySpider.getTimeOut() == null ? 5000 : mySpider.getTimeOut() * 1000);
    }
}