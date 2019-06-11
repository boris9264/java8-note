package com.boris.java8.chapter6;

import java.util.stream.LongStream;

/**
 * @Author: boris
 * @Data: Created on 2019/6/5
 * @Description:
 */
public class ParallelRangedSum {
    //1. 没有装箱拆箱的操作
    //2. rangeClosed固定了计算的范围 容易拆分成多个线程执行
    public static Long rangedSum(Long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }
}
