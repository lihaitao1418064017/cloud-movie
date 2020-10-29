package org.lht.boot.lang.lamda;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.lht.boot.lang.DeviceSummaryVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description
 * @date 2020/10/21 10:49
 **/
public class Main {


    @Test
    public void test01() {
        ArrayList<DeviceSummaryVO> deviceSummaryVOS = Lists.newArrayList(new DeviceSummaryVO(), new DeviceSummaryVO());
        List<String> collect = deviceSummaryVOS.stream().map(DeviceSummaryVO::getDeptId).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println(collect);
    }
}
