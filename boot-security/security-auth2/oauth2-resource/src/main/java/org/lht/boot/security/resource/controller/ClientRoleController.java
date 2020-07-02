package org.lht.boot.security.resource.controller;

import org.lht.boot.web.api.param.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiHaitao
 * @description ClientRoleController:
 * @date 2020/7/2 10:41
 **/
@RestController
@RequestMapping("/client/role")
public class ClientRoleController {
    @GetMapping
    public R test() {
        return R.ok();
    }
}
