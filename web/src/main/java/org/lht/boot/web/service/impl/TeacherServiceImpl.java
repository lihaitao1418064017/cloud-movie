package org.lht.boot.web.service.impl;

import org.lht.boot.web.dao.TeacherDao;
import org.lht.boot.web.domain.entity.Teacher;
import org.lht.boot.web.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/1/12 9:11 PM
 **/
@Service
public class TeacherServiceImpl extends AbstractEsCrudServiceImpl<Teacher, String, TeacherDao> implements TeacherService {

}
