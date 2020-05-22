package org.lht.boot.lang;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.lht.boot.lang.util.BeanUtils;
import org.lht.boot.lang.util.RandomUtils;

import java.util.ArrayList;
import java.util.Iterator;
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
    public void test03() {
        DeviceSummaryVO deviceSummaryVO = new DeviceSummaryVO();
        deviceSummaryVO.setDeptName("1級");
        deviceSummaryVO.setNum(0);

        DeviceSummaryVO deviceSummaryVO1 = new DeviceSummaryVO();
        deviceSummaryVO1.setDeptName("2級");
        deviceSummaryVO1.setNum(0);

        deviceSummaryVO.setChildren(Lists.newArrayList(deviceSummaryVO1));

        DeviceSummaryVO deviceSummaryVO2 = new DeviceSummaryVO();
        deviceSummaryVO2.setDeptName("3級");
        deviceSummaryVO2.setNum(1);
        deviceSummaryVO1.setChildren(Lists.newArrayList(deviceSummaryVO2));


        DeviceSummaryVO deviceSummaryVO3 = new DeviceSummaryVO();
        deviceSummaryVO3.setDeptName("4級");
        deviceSummaryVO3.setNum(0);

        deviceSummaryVO2.setChildren(Lists.newArrayList(deviceSummaryVO3));

        DeviceSummaryVO deviceSummaryVO4 = new DeviceSummaryVO();
        deviceSummaryVO4.setDeptName("5級");
        deviceSummaryVO4.setNum(0);

        deviceSummaryVO3.setChildren(Lists.newArrayList(deviceSummaryVO4));
        ArrayList<DeviceSummaryVO> deviceSummaryVOS = Lists.newArrayList(deviceSummaryVO);
        deepTree(deviceSummaryVOS);

        log.info("deviceSummary:{}", deviceSummaryVOS);

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
        log.info("cost :{}", end - start);

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


    void deepTree(List<DeviceSummaryVO> deviceSummaryVOS) {
        Iterator<DeviceSummaryVO> iterator = deviceSummaryVOS.iterator();
        while (iterator.hasNext()) {
            DeviceSummaryVO next = iterator.next();

            List<DeviceSummaryVO> children = next.getChildren();
            if (CollectionUtil.isNotEmpty(children)) {
                Iterator<DeviceSummaryVO> deviceSummaryVOIterator = children.iterator();
                while (deviceSummaryVOIterator.hasNext()) {
                    DeviceSummaryVO deviceSummaryVO = deviceSummaryVOIterator.next();
                    deepTree(deviceSummaryVO.getChildren());
                }
                int sum = children.stream().mapToInt(DeviceSummaryVO::getNum).sum();
                next.setNum(sum + next.getNum());
            }
        }

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
