package org.lht.boot.lang.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author LiHaitao
 * @description Vo:
 * @date 2020/3/31 13:37
 **/
@Data
public class Vo extends JSONObject {

    private JSONObject data;
}
