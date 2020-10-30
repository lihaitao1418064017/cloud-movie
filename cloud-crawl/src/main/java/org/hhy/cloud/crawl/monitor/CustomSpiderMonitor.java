package org.hhy.cloud.crawl.monitor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.SpiderListener;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.management.JMException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiHaitao
 * @description 自定义spider监控
 * @date 2020/10/30 14:51
 **/

public class CustomSpiderMonitor extends SpiderMonitor {

    private static CustomSpiderMonitor INSTANCE = new CustomSpiderMonitor();


    /**
     * todo 暂时map，后期redis存放，进行实时查询测试监控
     */
    private ConcurrentHashMap<String, CustomSpiderStatusMXBean> spiderStatusMXBeanMap = new ConcurrentHashMap<>();

    /**
     * 获取所有spider的SpiderStatusMXBean
     *
     * @return
     */
    public ConcurrentHashMap<String, CustomSpiderStatusMXBean> getSpiderStatusMXBeanMap() {
        return spiderStatusMXBeanMap;
    }

    @Override
    public synchronized CustomSpiderMonitor register(Spider... spiders) throws JMException {
        Spider[] var2 = spiders;
        int var3 = spiders.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Spider spider = var2[var4];
            SpiderMonitor.MonitorSpiderListener monitorSpiderListener = new SpiderMonitor.MonitorSpiderListener();
            if (spider.getSpiderListeners() == null) {
                List<SpiderListener> spiderListeners = new ArrayList();
                spiderListeners.add(monitorSpiderListener);
                spider.setSpiderListeners(spiderListeners);
            } else {
                spider.getSpiderListeners().add(monitorSpiderListener);
            }

            String key = spider.getUUID();
            CustomSpiderStatusMXBean customSpiderStatusMXBean = this.getSpiderStatusMBean(spider, monitorSpiderListener);
            spiderStatusMXBeanMap.put(key, customSpiderStatusMXBean);
            this.registerMBean(customSpiderStatusMXBean);
        }

        return this;
    }

    @Override
    protected CustomSpiderStatusMXBean getSpiderStatusMBean(Spider spider, CustomSpiderMonitor.MonitorSpiderListener monitorSpiderListener) {
        return new CustomSpiderStatus(spider, monitorSpiderListener);
    }

    public static CustomSpiderMonitor instance() {
        return INSTANCE;
    }


}
