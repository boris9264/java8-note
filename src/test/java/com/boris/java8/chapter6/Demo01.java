package com.boris.java8.chapter6;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author: boris
 * @Data: Created on 2019/6/5
 * @Description: 并行流demo
 */
public class Demo01 {
    //高效的使用并行流
    //1. 数据量足够大的时候
    //2. 执行过程中避免依赖元素顺序的操作 如findFirst limit
    //3. 考虑数据结构是否易于拆分 List拆分高效 LinkList 拆分性能较差
    //4. 执行过程中避免装箱拆箱操作
    //.sequential()转换顺序流

    @Test
    public void test1() {
        System.out.println(numberSum(100L));
    }

    //1到给定的自然数求和
    public long numberSum(Long n) {
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    //Iterator不易于并行处理
    @Test
    public void test2() {
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");
    }

    //LongStream易于并行处理
    @Test
    public void test3() {
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelRangedSum::rangedSum, 10_000_000) + " msecs");
    }

    public long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    @Test
    public void forkJoinTest() {
        long start = System.currentTimeMillis();
        long[] numbers = LongStream.rangeClosed(1, 2000000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);
    }

}
