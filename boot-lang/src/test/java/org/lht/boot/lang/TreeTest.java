package org.lht.boot.lang;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.lang.util.RandomUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiHaitao
 * @description TreeTest:
 * @date 2020/4/16 11:20
 **/
@Slf4j
public class TreeTest {


    private List<DeviceSummaryVO> deviceSummaryVOList = Lists.newArrayList();

    private List<DeviceSummaryVO> deviceSummaryVOS = Lists.newArrayList();

    @Before
    public void before() {
        Long i = 0L;
        DeviceSummaryVO deviceSummaryVO = new DeviceSummaryVO();
        deviceSummaryVO.setDeptName(RandomUtils.randomName());
        deviceSummaryVO.setDeptPath(RandomUtils.randomString(
                "1qweoriutqweiurkjhdkafhmzbvn987324189735134z,llasd1!@#$%^&*()", 6));
        deviceSummaryVO.setDeptId(RandomUtil.randomString(20));
        deviceSummaryVO.setChildren(Lists.newArrayList());
        deviceSummaryVO.setDeptParentId(null);
        DeviceSummaryVO tree = initTree(deviceSummaryVO);
        deviceSummaryVOList.add(tree);
    }


    @Test
    public void digui() {
        long start = System.currentTimeMillis();
        List<PlainDepartment> deep = deep(deviceSummaryVOList);
        long end = System.currentTimeMillis();
        log.info("const :{}", end - start);


    }


    @Test
    public void bianli() {
        long start = System.currentTimeMillis();

        List<PlainDepartment> plainDepartments = Lists.newArrayList();
        for (DeviceSummaryVO deviceSummaryVO : deviceSummaryVOS) {
            PlainDepartment plainDepartment = new PlainDepartment();
            BeanUtils.copyProperties(deviceSummaryVO, plainDepartment);
            plainDepartments.add(plainDepartment);
        }
        long middle = System.currentTimeMillis();
        log.info("->>>>>>>>>>>>>{}", middle - start);
        List<PlainDepartment> dataList = com.google.common.collect.Lists.newArrayList();
        for (PlainDepartment viidSummaryByDeptVO : plainDepartments) {
            long count = plainDepartments.stream().filter(parent3 -> parent3.getDeptId() != null && parent3.getDeptId().equals(viidSummaryByDeptVO.getDeptParentId())).count();
            if (count == 0) {
                dataList.add(viidSummaryByDeptVO);
            }
            //            for (PlainDepartment it : plainDepartments) {
            //                if (null == viidSummaryByDeptVO.getChildren()) {
            //                    viidSummaryByDeptVO.setChildren(new ArrayList<PlainDepartment>());
            //                }
            //                if (StrUtil.isNotBlank(it.getDeptParentId()) && it.getDeptParentId().equals(viidSummaryByDeptVO.getDeptId())) {
            //                    viidSummaryByDeptVO.getChildren().add(it);
            //                }
            //            }
        }
        long end = System.currentTimeMillis();
        log.info("const :{}", end - start);

    }


    List<PlainDepartment> deep(List<DeviceSummaryVO> deviceSummaryVOS) {
        List<PlainDepartment> collect = deviceSummaryVOS.stream().map(p -> {
            PlainDepartment plainDepartment = new PlainDepartment();
            BeanUtils.copyProperties(p, plainDepartment);
            List<PlainDepartment> child = com.google.common.collect.Lists.newArrayList();
            if (CollectionUtil.isNotEmpty(p.getChildren())) {
                child = deep(p.getChildren());
            }
            plainDepartment.setChildren(child);
            return plainDepartment;
        }).collect(Collectors.toList());
        return collect;
    }

    private DeviceSummaryVO initTree(DeviceSummaryVO deviceSummaryVO) {
        if (deviceSummaryVO.getDeptPath().split("-").length == 100) {
            return deviceSummaryVO;
        }
        for (int i = 0; i < 100; i++) {
            DeviceSummaryVO vo = new DeviceSummaryVO();
            vo.setDeptId(RandomUtil.randomString(20));
            vo.setDeptParentId(deviceSummaryVO.getDeptId());
            vo.setDeptName(RandomUtils.randomName());
            vo.setDeptPath(deviceSummaryVO.getDeptPath() + "-" + RandomUtils.randomString(
                    "1qweoriutqweiurkjhdkafhmzbvn987324189735134z,llasd1!@#$%^&*()", 6));
            deviceSummaryVO.getChildren().add(vo);
            deviceSummaryVOS.add(vo);
        }
        initTree(deviceSummaryVO.getChildren().get(0));
        //        if (vo.getDeptPath().split("-").length == 10000) {
        //            return deviceSummaryVO;
        //        }
        //        initTree(vo1);
        //        if (vo1.getDeptPath().split("-").length == 10000) {
        //            return deviceSummaryVO;
        //        }
        return deviceSummaryVO;
    }
}
