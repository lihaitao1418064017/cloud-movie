package org.hhy.cloud.crawl.utils;

import us.codecraft.webmagic.selector.Html;

/**
 * http 解析类
 */
public class HttpUtil {

    private static DefaultHttpClientDownloader httpClientDownloader = new DefaultHttpClientDownloader();

    public static Html download(String url) {
        return httpClientDownloader.download(url);
    }
}
