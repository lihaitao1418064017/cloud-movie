package org.lht.boot.security.server.controller;

import org.lht.boot.lang.util.R;
import org.lht.boot.security.server.domain.entity.Project;
import org.lht.boot.security.server.domain.vo.ProjectVO;
import org.lht.boot.security.server.service.ProjectService;
import org.lht.boot.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description 项目
 * @date 2020/7/14 18:44
 **/
@RestController
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, Integer, ProjectVO, ProjectService> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public R<Integer> add(ProjectVO data) {
        data.setClientSecret(passwordEncoder.encode(data.getClientSecret()));
        return super.add(data);
    }
}
