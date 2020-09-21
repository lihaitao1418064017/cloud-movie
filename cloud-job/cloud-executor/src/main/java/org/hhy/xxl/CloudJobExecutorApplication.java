package org.hhy.xxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author LiuHao
 * @date 2020/9/13 23:57
 * @description cloud-job执行器执行类  xxl-job框架
*/
@SpringBootApplication
@EnableTransactionManagement
public class CloudJobExecutorApplication {

	public static void main(String[] args) {
        SpringApplication.run(CloudJobExecutorApplication.class, args);
	}

}