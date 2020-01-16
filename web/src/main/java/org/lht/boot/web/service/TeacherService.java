package org.lht.boot.web.service;

import org.lht.boot.web.dao.TeacherDao;
import org.lht.boot.web.domain.entity.Teacher;

/**
 * Description:
 *
 * @Author lht
 * @Date 2020/1/12 9:08 PM
 **/
public interface TeacherService extends AbstractEsCrudService<Teacher, String, TeacherDao> {
}
