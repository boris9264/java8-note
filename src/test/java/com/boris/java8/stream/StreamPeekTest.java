package com.boris.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: boris
 * @Data: Created on 2019/6/12
 * @Description: 使用peek对stream操作过程跟踪调试
 */
public class StreamPeekTest {
    @Test
    public void peekTest() {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        List<Integer> result = numbers.stream()
                .peek(a -> System.out.println("from stream: " + a))
                .map(a -> a+17)
                .peek(a -> System.out.println("after map: " + a))
                .limit(3)
                .peek(a -> System.out.println("after limit: " + a))
                .collect(Collectors.toList());


    }
}
