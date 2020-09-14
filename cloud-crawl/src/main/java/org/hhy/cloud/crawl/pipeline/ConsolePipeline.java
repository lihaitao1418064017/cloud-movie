package org.hhy.cloud.crawl.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @Classname ConsolePipeline
 * @Description 自定义Pipeline
 * @Date 2020/9/14 10:28 PM
 * @Created by yupeng
 */
public class ConsolePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("1111111111111");
    }
}
