package com.boris.java8.stream;

import com.boris.java8.pojo.Dish;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: boris
 * @Data: Created on 2019/5/23
 * @Description: 收集器相关demo
 */
public class CollectDemo {
    List<Dish> menu = new ArrayList<>();

    @Before
    public void initMenu() {
        menu = Arrays.asList(
                new Dish("pork", 800, false, Dish.Type.MEAT),
                new Dish("beef", 700, false, Dish.Type.MEAT),
                new Dish("chicken", 400, false, Dish.Type.MEAT),
                new Dish("french fries", 530, true, Dish.Type.OTHER),
                new Dish("rice", 350, true, Dish.Type.OTHER),
                new Dish("season fruit", 120, true, Dish.Type.OTHER),
                new Dish("pizza", 550, true, Dish.Type.OTHER),
                new Dish("prawns", 200, false, Dish.Type.FISH),
                new Dish("prawns", 300, false, Dish.Type.FISH),
                new Dish("salmon", 450, false, Dish.Type.FISH));
    }

    //求卡路里平均值 (收集器还可以用于计算最大, 最小值, 总和)
    @Test
    public void averageTest() {
        double average = menu.stream()
                .collect(Collectors.averagingDouble(a -> a.getCalories()));

        System.out.println(average);
    }

    //IntSummaryStatistics 可以将流中得各种统计数据汇总
    @Test
    public void summaryTest() {
        IntSummaryStatistics intSummaryStatistics = menu.stream()
                .collect(Collectors.summarizingInt(a -> a.getCalories()));

        System.out.println(JsonUtil.toString(intSummaryStatistics));
    }

    // joining连接字符串
    // 如果流中的元素有toString方法则无需.map
    @Test
    public void joiningTest() {
        String dishNameStr = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(","));

        System.out.println(dishNameStr);
    }
}
