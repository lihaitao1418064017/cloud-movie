package org.hhy.cloud.crawl.monitor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.monitor.SpiderStatus;

import java.util.Date;

/**
 * @author LiHaitao
 * @description 自定义SpiderStatus
 * @date 2020/10/30 15:38
 **/
public class CustomSpiderStatus extends SpiderStatus implements CustomSpiderStatusMXBean {

    private Date stopTime;

    public CustomSpiderStatus(Spider spider, SpiderMonitor.MonitorSpiderListener monitorSpiderListener) {
        super(spider, monitorSpiderListener);
    }


    @Override
    public Date getStopTime() {
        return stopTime;
    }


    @Override
    public void stop() {
        this.spider.stop();
        stopTime = new Date();
    }
}
