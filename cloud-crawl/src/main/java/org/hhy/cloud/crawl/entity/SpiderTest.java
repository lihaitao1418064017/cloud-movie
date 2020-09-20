package org.hhy.cloud.crawl.entity;

import lombok.Data;

/**
 * @Classname SpiderTest
 * @Description 测试页面实体类
 * @Date 2020/9/13 10:29 PM
 * @Created by yupeng
 */
@Data
public class SpiderTest {


    /**
     * 测试页面URL
     */
    private String testUrl;

    /**
     * 页面中正则表达式
     */
    private String regex;

    /**
     * 提取内容
     */
    private String xpath;

}
