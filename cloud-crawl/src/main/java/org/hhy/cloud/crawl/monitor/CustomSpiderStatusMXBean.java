package org.hhy.cloud.crawl.monitor;

import us.codecraft.webmagic.monitor.SpiderStatusMXBean;

import java.util.Date;

/**
 * @author LiHaitao
 * @description 自定义爬虫状态接口
 * @date 2020/10/30 16:32
 **/
public interface CustomSpiderStatusMXBean extends SpiderStatusMXBean {

    Date getStopTime();
}
