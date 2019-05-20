package com.boris.java8.stream;

import com.boris.java8.util.JsonUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: boris
 * @Data: Created on 2019/5/20
 * @Description: 数值流练习
 */
public class NumberStreamTest {
    //生成1-100的偶数
    @Test
    public void rangeTest() {
        IntStream result = IntStream.rangeClosed(1, 100)
                .filter(i -> i%2==0);

        result.forEach(value -> System.out.println(value));
    }

    //生成100以内的勾股数 a*a+b*b=c*c [a,b,c]
    @Test
    public void test() {
        Stream<int[]> resultStream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a*a + b*b) %1==0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)}))
                .limit(5);

        List<int[]> resultList = resultStream.collect(Collectors.toList());
        System.out.println(JsonUtil.toString(resultList));


    }
}
