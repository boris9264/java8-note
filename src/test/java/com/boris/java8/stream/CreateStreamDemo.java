package com.boris.java8.stream;

import com.boris.java8.util.JsonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: boris
 * @Data: Created on 2019/5/22
 * @Description: 构建流
 */
public class CreateStreamDemo {

    //由值创建流
    @Test
    public void test1() {
        Stream<String> stringStream = Stream.of("a","b","c");
        stringStream.forEach(s -> System.out.println(s));
    }

    //由数组创建流
    @Test
    public void test2() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    //迭代生成流
    //斐波那契数列
    @Test
    public void test3() {
        List<Integer> result = Stream
                .iterate(new int[]{0,1}, a -> new int[]{a[1], a[0] + a[1]})
                .map(a -> a[0])
                .limit(20)
                .collect(Collectors.toList());
        System.out.println(JsonUtil.toString(result));
    }

}
