package com.boris.java8.chapter6;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: boris
 * @Data: Created on 2019/6/10
 * @Description: 使用分支/合并框架求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final long THRESHOLD = 10_000;

    private long[] numbers;
    private int start;
    private int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);

        long rightResult = rightTask.compute();
        long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    /*
        1.对一个任务调用join方法会阻塞调用方，直到该任务做出结果。因此，有必要在两个子
          任务的计算都开始之后再调用它。否则，你得到的版本会比原始的顺序算法更慢更复杂，
          因为每个子任务都必须等待另一个子任务完成才能启动。

        2.对子任务调用fork方法可以把它排进ForkJoinPool。同时对左边和右边的子任务调用
          它似乎很自然，但这样做的效率要比直接对其中一个调用compute低。这样做你可以为
          其中一个子任务重用同一线程，从而避免在线程池中多分配一个任务造成的开销。

        3.不应该在RecursiveTask内部使用ForkJoinPool的invoke方法。相反，你应该始终直
          接调用compute或fork方法，只有顺序代码才应该用invoke来启动并行计算。

    */
}
