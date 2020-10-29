package org.hhy.cloud.crawl.service.impl;

import org.hhy.cloud.crawl.dao.CloudVideoDao;
import org.hhy.cloud.crawl.entity.CloudVideo;
import org.hhy.cloud.crawl.service.CloudVideoService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @description: 视频
 * @author: LiHaitao
 * @date: 2020/10/29 20:12
 */
@Service
public class CloudVideoServiceImpl extends BaseMybatisCrudServiceImpl<CloudVideo, String, CloudVideoDao> implements CloudVideoService {


}
