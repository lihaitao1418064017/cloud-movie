package org.hhy.cloud.crawl.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SpiderContentTest
 * @Description TODO
 * @Date 2020/9/13 11:02 PM
 * @Created by yupeng
 */
@Data
public class SpiderContentTest {

    @ApiModelProperty(notes = "入口页网址")
    private String entryUrl;

    @ApiModelProperty(notes = "正文页xpath")
    private String contentXpath;

    @ApiModelProperty(notes = "字段xpath")
    private String xpath;

}
