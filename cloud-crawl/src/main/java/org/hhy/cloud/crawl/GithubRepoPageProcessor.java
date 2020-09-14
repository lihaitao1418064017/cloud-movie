package org.hhy.cloud.crawl;

import org.hhy.cloud.crawl.utils.DefaultHttpClientDownloader;
import org.hhy.cloud.crawl.pipeline.ConsolePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.processor.PageProcessor;

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
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
        GithubRepo githubRepo = new GithubRepo();
        if (githubRepo.getName() == null) {
            //skip this page
            page.setSkip(true);
        } else {
            page.putField("repo", githubRepo);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor())
                .addUrl("https://github.com/code4craft")
                .addPipeline(new ConsolePipeline())
                .setDownloader(new DefaultHttpClientDownloader())
                .thread(1)
                .run();
    }

}
