package com.boris.java8.chapter6;

import java.util.stream.Stream;

/**
 * @Author: boris
 * @Data: Created on 2019/6/5
 * @Description:
 */
public class ParallelStreams {
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
}
