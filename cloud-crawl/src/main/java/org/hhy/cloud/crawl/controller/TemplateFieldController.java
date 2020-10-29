package org.hhy.cloud.crawl.controller;

import org.hhy.cloud.crawl.entity.TemplateField;
import org.hhy.cloud.crawl.service.TemplateFieldService;
import org.hhy.cloud.crawl.vo.TemplateFieldVO;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description 模板字段控制器
 * @date 2020/10/29 20:16
 **/
@RestController
@RequestMapping("/template_field")
public class TemplateFieldController extends AbstractController<TemplateField,String,TemplateFieldVO,TemplateFieldService> {
}
