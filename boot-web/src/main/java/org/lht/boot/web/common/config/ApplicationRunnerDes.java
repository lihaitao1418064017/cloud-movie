package org.lht.boot.web.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author LiHaitao
 * @description ApplicationRunnerDes:
 * @date 2020/3/19 10:06
 **/
@Slf4j
@Component
public class ApplicationRunnerDes implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("web框架启动成功............................");
    }
}
