package com.boris.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author: boris
 * @Data: Created on 2019/5/14
 * @Description:
 */
public class ReduceTest {
    @Test
    public void sumTest() {
        //数组求和
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        Integer sum = numbers.stream().reduce(0, (a,b) -> a + b);
        System.out.println(sum);

        //数组乘积
        System.out.println(numbers.stream().reduce(1, (a, b) -> a * b));

        Optional<Integer> sumOptional = numbers.stream().reduce((a, b) -> a * b);
        System.out.println(sumOptional.get());
    }

    //数组中的最大最小值
    @Test
    public void testMinMax() {
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        Optional<Integer> max = numbers.stream().reduce((a,b) -> a>b ? a : b);
        System.out.println(max.get());

        Optional<Integer> min = numbers.stream().reduce((a,b) -> a<b ? a : b);
        System.out.println(min.get());
    }
}
