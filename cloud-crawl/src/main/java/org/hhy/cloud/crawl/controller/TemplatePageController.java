package org.hhy.cloud.crawl.controller;

import org.hhy.cloud.crawl.entity.TemplatePage;
import org.hhy.cloud.crawl.service.TemplatePageService;
import org.hhy.cloud.crawl.vo.TemplatePageVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description 模板页控制器
 * @date 2020/10/29 20:16
 **/
@RestController
@RequestMapping("/template_page")
public class TemplatePageController extends AbstractController<TemplatePage, String, TemplatePageVO, TemplatePageService> {
}
