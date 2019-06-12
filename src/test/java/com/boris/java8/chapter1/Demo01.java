package com.boris.java8.chapter1;

import com.boris.java8.pojo.Apple;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * @Author: boris
 * @Data: Created on 2019/5/6
 * @Description:
 */
public class Demo01 {
    List<Apple> inventory = new ArrayList<>();
    @Before
    public void initInventory() {
        Apple apple = new Apple("green", 120);
        inventory.add(apple);

        Apple apple11 = new Apple("red", 160);
        inventory.add(apple11);

        inventory.add(new Apple("red", 130));
    }

    //根据颜色筛选苹果
    @Test
    public void test01() {
        //红色的苹果
        Predicate<Apple> predicate = apple -> apple.getColor().equals("red");
        List<Apple> result = filterApples(inventory, predicate);
        System.out.println(JsonUtil.toString(result));

        //重量大于150的苹果
        Predicate<Apple> weightPredicate = apple -> apple.getWeight() > 150;
        result = filterApples(result, weightPredicate);
        System.out.println(JsonUtil.toString(result));

    }

    //根据重量排序
    @Test
    public void sortTest() {
//        inventory.sort((Apple a1, Apple a2)-> a2.getWeight().compareTo(a1.getWeight()));

        //reversed可以倒序排列
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        inventory.stream().filter(a -> {
            System.out.println(JsonUtil.toString(a));return a.getColor().equals("red");});
        System.out.println(JsonUtil.toString(inventory));
    }

    //启动线程
    @Test
    public void threadTest() {
        Thread thread = new Thread(() -> System.out.println("Thread running..."));
        thread.start();
    }

    public <T> List<T> filterApples(List<T> inventory, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : inventory) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
