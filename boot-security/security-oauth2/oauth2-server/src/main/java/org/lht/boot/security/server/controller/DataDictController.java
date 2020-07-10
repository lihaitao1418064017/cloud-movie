package org.lht.boot.security.server.controller;

import org.lht.boot.security.resource.entity.DataDict;
import org.lht.boot.security.resource.service.DataDictService;
import org.lht.boot.security.resource.vo.DataDictVO;
import org.lht.boot.web.controller.AbstractController;
import org.lht.boot.web.controller.AbstractTreeController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 数据字典
 *
 * @Author lht
 * @Date 2020/7/10 下午9:23
 **/
@RestController
@RequestMapping("/data_dict")
public class DataDictController extends AbstractTreeController<DataDict, String, DataDictVO, DataDictService> {

}
