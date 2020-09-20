package org.hhy.cloud.crawl;

import org.hhy.cloud.crawl.utils.DefaultHttpClientDownloader;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @Classname GithubRepoPageProcessor
 * @Description TODO
 * @Date 2020/9/13 7:51 PM
 * @Created by yupeng
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        List<String> targetUrl = page.getHtml().xpath("//*div[@class='recommendations-bd").links().regex("https://movie\\.douban\\.com/subject.*").all();
        page.addTargetRequests(targetUrl);
        String content = page.getHtml().xpath("//div[@id='content']/h1/span[1]/text()").get();

        // 非法数据，跳过
        if (StringUtils.isEmpty(content)) {
            page.setSkip(true);
        }else {
            page.putField("title", content);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://movie.douban.com/subject/26662193/?from=showing")
                .addPipeline(new ConsolePipeline())
                .setDownloader(new DefaultHttpClientDownloader())
                .thread(1)
                .run();
    }

}
