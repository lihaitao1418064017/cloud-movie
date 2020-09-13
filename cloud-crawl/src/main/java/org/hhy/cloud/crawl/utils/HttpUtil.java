package org.hhy.cloud.crawl.utils;

import org.hhy.cloud.crawl.config.DefaultHttpClientDownloader;
import us.codecraft.webmagic.selector.Html;

/**
 * 2020/9/6 14:22
 * yechangjun
 */
public class HttpUtil {

    private static DefaultHttpClientDownloader httpClientDownloader = new DefaultHttpClientDownloader();

    public static Html download(String url) {
        return httpClientDownloader.download(url);
    }
}
