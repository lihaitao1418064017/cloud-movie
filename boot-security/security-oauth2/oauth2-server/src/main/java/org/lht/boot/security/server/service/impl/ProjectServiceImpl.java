package org.lht.boot.security.server.service.impl;

import org.lht.boot.security.server.dao.ProjectDao;
import org.lht.boot.security.server.domain.entity.Project;
import org.lht.boot.security.server.service.ProjectService;
import org.lht.boot.web.service.impl.BaseMybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author LiHaitao
 * @description 项目管理
 * @date 2020/7/14 17:09
 **/
@Service
public class ProjectServiceImpl extends BaseMybatisCrudServiceImpl<Project, Integer, ProjectDao> implements ProjectService {
}
