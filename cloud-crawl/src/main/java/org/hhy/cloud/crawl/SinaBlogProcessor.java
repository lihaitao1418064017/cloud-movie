package org.hhy.cloud.crawl;

import org.hhy.cloud.crawl.monitor.CustomSpiderMonitor;
import org.hhy.cloud.crawl.monitor.CustomSpiderStatusMXBean;
import org.hhy.cloud.crawl.pipeline.CustomPipeline;
import org.hhy.cloud.crawl.pipeline.CustomProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname SinaBlogProcessor
 * @Description TODO
 * @Date 2020/9/20 6:26 PM
 * @Created by yupeng
 */
public class SinaBlogProcessor implements PageProcessor {


    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {


        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {


            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {

            String s = page.getUrl().get();
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            //            page.putField("date",
            //                    page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws JMException {
        Spider spider = Spider.create(new SinaBlogProcessor());
        spider.setUUID("123");
        CustomSpiderMonitor register = CustomSpiderMonitor.instance().register(spider);
        spider.addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html")
//                .addPipeline(new CustomPipeline())
                .run();

        ConcurrentHashMap<String, CustomSpiderStatusMXBean> spiderStatusMXBeanMap = register.getSpiderStatusMXBeanMap();
        CustomSpiderStatusMXBean customSpiderStatusMXBean = spiderStatusMXBeanMap.get("123");
        customSpiderStatusMXBean.getErrorPageCount();

    }
}
