package org.lht.boot.security.server.service;

import org.lht.boot.security.server.domain.entity.DashBoard;

/**
 * @author LiHaitao
 * @description 主页服务
 * @date 2020/9/3 16:07
 **/
public interface DashBoardService {

    DashBoard select(String username);
}
