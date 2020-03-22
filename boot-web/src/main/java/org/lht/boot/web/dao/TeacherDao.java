package org.lht.boot.web.dao;

import org.lht.boot.web.domain.entity.Teacher;
import org.springframework.stereotype.Repository;

/**
 * Es
 */
@Repository
public class TeacherDao extends ElasticSearchCrudDao<Teacher, String> {


}
