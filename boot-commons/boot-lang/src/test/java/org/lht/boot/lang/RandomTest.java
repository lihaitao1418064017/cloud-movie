package org.lht.boot.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.lht.boot.lang.util.RandomUtils;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author LiHaitao
 * @description RandomTest:
 * @date 2020/3/11 16:00
 **/
@Slf4j
public class RandomTest {

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            log.info("grade:  {}", RandomUtils.randomGrade());
        }


    }

    @Test
    public void test05() {


        String isUnique = null;
        if (isUnique == null) {
            String resourcePoolReport = null;
            isUnique = resourcePoolReport == null ? "true" : "false";
        }
        System.out.println("true".equals(isUnique));
    }

    @Test
    public void test02() {
        for (int i = 0; i < 10; i++) {
            log.info("random:{}", RandomUtils.randomInt(2));
        }

        IntStream.range(1, 3).forEach(System.out::println);
        final OptionalDouble average = IntStream.rangeClosed(1, 4).average();
        final double asDouble = average.getAsDouble();
        System.out.println(asDouble);
        //        IntStream.rangeClosed(1, 3).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .distinct() //去重元素2
                .forEach(System.out::println);

        //使用flatMap找出单词列表中各不相同的字符
        List<String> words = Arrays.asList("Hello", "World");

        final List<String> collect = words.stream().map(a -> a.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
        List<String> wordMap = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(wordMap);

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4, 5);
        List<int[]> pairs = num1
                .stream()
                .flatMap(i -> num2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs
                .stream()
                .forEach(i -> {
                    Arrays.stream(i).forEach(System.out::print);
                });
    }


    @Test
    public void test04() {
        //
        //        List<DeviceSummaryVO> deviceSummaryVOS = Lists.newArrayList();
        //        String path = "";
        //        for (int i = 0; i < 10000; i++) {
        //            DeviceSummaryVO deviceSummaryVO = new DeviceSummaryVO();
        //            deviceSummaryVO.setDeptPath(path + i);
        //            deviceSummaryVO.setPeopleNum(RandomUtil.randomLong(1000));
        //            deviceSummaryVO.setDeviceTotalNum(RandomUtil.randomLong(1000));
        //            deviceSummaryVOS.add(deviceSummaryVO);
        //        }
        //        Long start = System.currentTimeMillis();
        //        List<DeviceSummaryVO> deviceSummaryVOList = Lists.newArrayList();
        //        for (DeviceSummaryVO deviceSummaryVO : deviceSummaryVOS) {
        //            DeviceSummaryVO deviceSummary = new DeviceSummaryVO();
        //            BeanUtils.copyProperties(deviceSummaryVO, deviceSummary);
        //            for (DeviceSummaryVO it : deviceSummaryVOS) {
        //                if (StrUtil.isNotBlank(it.getDeptPath())
        //                        && it.getDeptPath().contains(deviceSummary.getDeptPath())
        //                        && !it.getDeptPath().equals(deviceSummary.getDeptPath())) {
        //                    Long peopleNum = deviceSummary.getPeopleNum();
        //                    peopleNum = peopleNum == null ? 0 : peopleNum;
        //                    Long sPeopleNum = it.getPeopleNum();
        //                    sPeopleNum = sPeopleNum == null ? 0 : sPeopleNum;
        //                    deviceSummary.setPeopleNum(peopleNum + sPeopleNum);
        //                    Long deviceTotalNum = deviceSummary.getDeviceTotalNum();
        //                    deviceTotalNum = deviceTotalNum == null ? 0 : deviceTotalNum;
        //                    Long sDeviceTotalNum = it.getDeviceTotalNum();
        //                    sDeviceTotalNum = sDeviceTotalNum == null ? 0 : sDeviceTotalNum;
        //                    deviceSummary.setDeviceTotalNum(deviceTotalNum + sDeviceTotalNum);
        //                }
        //            }
        //            deviceSummaryVOList.add(deviceSummary);
        //        }
        //
        //        long end = System.currentTimeMillis();
        //        System.out.println(end - start);
    }
}