package org.hhy.cloud.crawl.controller;

import org.hhy.cloud.crawl.entity.CloudVideo;
import org.hhy.cloud.crawl.service.CloudVideoService;
import org.hhy.cloud.crawl.vo.CloudVideoVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 视频数据管理
 * @author: LiHaitao
 * @date: 2020/10/29 20:13
 */
@RestController
@RequestMapping("/cloud_video")
public class CloudVideoController extends AbstractController<CloudVideo, String, CloudVideoVO, CloudVideoService> {


}
