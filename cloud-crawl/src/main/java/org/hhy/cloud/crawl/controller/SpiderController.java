package org.hhy.cloud.crawl.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hhy.cloud.crawl.entity.SpiderContentTest;
import org.hhy.cloud.crawl.entity.SpiderTest;
import org.hhy.cloud.crawl.utils.HttpUtil;
import org.lht.boot.lang.util.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @Classname SpiderController
 * @Description TODO
 * @Date 2020/9/13 10:35 PM
 * @Created by yupeng
 */
@RestController("/spider")
public class SpiderController {

    /**
     * 返回测试连接html内容
     * @param spiderTest
     * @return
     */
    @PostMapping("/test-page")
    @ResponseBody
    public R<String> testPage(@RequestBody SpiderTest spiderTest) {
        if (StringUtils.isBlank(spiderTest.getTestUrl())) {
            return R.error("请输入地址");
        }
        Html html = HttpUtil.download(spiderTest.getTestUrl());
        return R.ok(html.toString());
    }


    @PostMapping("/test-xpath")
    @ResponseBody
    public R<List<String>> testXpath(@RequestBody SpiderTest spiderTest) {
        if (StringUtils.isBlank(spiderTest.getTestUrl())) {
            return R.error("请输入地址");
        }
        if (StringUtils.isBlank(spiderTest.getXpath())) {
            return R.error("请输入XPATH");
        }
        Html html = HttpUtil.download(spiderTest.getTestUrl());
        return R.ok(html.xpath(spiderTest.getXpath()).all());
    }

    @PostMapping("/test-content-xpath")
    @ApiOperation("测试正文页xpath")
    @ResponseBody
    public R<List<String>> testContentXpath(@RequestBody SpiderContentTest contentTest) {
        if (StringUtils.isBlank(contentTest.getEntryUrl())) {
            return R.error("请输入地址");
        }
        if (StringUtils.isBlank(contentTest.getContentXpath())) {
            return R.error("请输入正文页XPATH");
        }
        if (StringUtils.isBlank(contentTest.getXpath())) {
            return R.error("请输入字段XPATH");        }
        Html html = HttpUtil.download(contentTest.getEntryUrl());
        List<String> links = html.xpath(contentTest.getContentXpath()).all();
        if (CollectionUtils.isEmpty(links)) {
            return new R<>();
        }
        String testLink = links.get(0);
        if (testLink.startsWith("//")) {
            testLink = "http:" + testLink;
        }
        Html testPage = HttpUtil.download(testLink);
        List<String> values = testPage.xpath(contentTest.getXpath()).all();
        values.add(0, testLink);
        return R.ok(values);
    }

    @PostMapping("/test-regex")
    @ApiOperation("测试正则")
    @ResponseBody
    public R<List<String>> testRegex(@RequestBody SpiderTest spiderTest) {
        if (StringUtils.isBlank(spiderTest.getTestUrl())) {
            return R.error("请输入地址");
        }
        if (StringUtils.isBlank(spiderTest.getRegex())) {
            return R.error("请输入正则表达式");
        }
        Html html = HttpUtil.download(spiderTest.getTestUrl());
        return R.ok(html.links().regex(spiderTest.getRegex()).all());
    }

}
