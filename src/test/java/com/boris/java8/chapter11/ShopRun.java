package com.boris.java8.chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @Author: boris
 * @Data: Created on 2019/6/17
 * @Description:
 */
public class ShopRun {
    List<Shop> shops = new ArrayList<>();
    @Before
    public void init() {
        shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"),
                new Shop("taobao"),
                new Shop("jingdong"),
                new Shop("pinduoduo"),
                new Shop("abc"),
                new Shop("bcd"),
                new Shop("ccc")

        );
    }

    private final Executor executor =
            Executors.newFixedThreadPool(Math.min(20, 100),
                    new ThreadFactory() {
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });

    //用流并行执行
    public List<String> findPrices(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    //用CompletableFuture 并自定义线程池(线程数量)
    //由于默认启动得线程数为处理器数量 单个线程执行时间过长时，任务需要排队等待线程释放
    public List<String> findPricesByComplete(String product) {
        List<CompletableFuture<String>> completableFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() ->
                        shop.getName() + " price is " +
                        shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return completableFutures.stream().map(CompletableFuture :: join).collect(Collectors.toList());
    }

    @Test
    public void streamRun() {
        long start = System.nanoTime();
        System.out.println(findPricesByComplete("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

/*
    1.如果你进行的是计算密集型的操作，并且没有I/O，那么推荐使用Stream接口，因为实
      现简单，同时效率也可能是最高的（如果所有的线程都是计算密集型的，那就没有必要
      创建比处理器核数更多的线程）。

    2.反之，如果你并行的工作单元还涉及等待I/O的操作（包括网络连接等待），那么使用
      CompletableFuture灵活性更好，你可以像前文讨论的那样，依据等待/计算，或者
      W/C的比率设定需要使用的线程数。这种情况不使用并行流的另一个原因是，处理流的
      流水线中如果发生I/O等待，流的延迟特性会让我们很难判断到底什么时候触发了等待。*/
}
