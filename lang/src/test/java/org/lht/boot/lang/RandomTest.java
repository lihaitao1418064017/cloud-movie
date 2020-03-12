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
                    Arrays.stream(i).forEach(System.out::println);
                });
    }
}