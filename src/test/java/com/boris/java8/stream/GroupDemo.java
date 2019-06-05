package com.boris.java8.stream;

import com.boris.java8.pojo.CaloricLevel;
import com.boris.java8.pojo.Dish;
import com.boris.java8.util.JsonUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: boris
 * @Data: Created on 2019/5/23
 * @Description: 分组demo
 */
public class GroupDemo {
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

    //根据菜单type分组
    @Test
    public void test1() {
        Map<Dish.Type, List<Dish>> groupMenu = menu.stream()
                .collect(Collectors.groupingBy(dish -> dish.getType()));

        System.out.println(JsonUtil.toString(groupMenu));
    }

    //把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700
    //卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
    @Test
    public void test2() {
        Map<CaloricLevel, List<Dish>> groupMenu = menu.stream()
                .collect(Collectors.groupingBy(dish ->{
                    if (dish.getCalories() > 700) {
                        return CaloricLevel.FAT;
                    } else if (dish.getCalories() > 400) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.DIET;
                    }
                }));

        System.out.println(JsonUtil.toString(groupMenu));
    }

    //多级分组 根据菜品类型分组 再根据菜品卡路里分组
    @Test
    public void testLevelGroup2() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType, Collectors.groupingBy(
                        dish -> {
                            if (dish.getCalories() > 700) {
                                return CaloricLevel.FAT;
                            } else if (dish.getCalories() > 400) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.DIET;
                            }
                        }
                )));

        System.out.println(JsonUtil.toString(result));
    }

    //根据菜单类型分组，统计每种菜单菜品数量
    @Test
    public void testGroupCount() {
        Map<Dish.Type, Long> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType, Collectors.counting()));

        System.out.println(JsonUtil.toString(result));
    }

    //根据菜单类型分组，查找每种菜单中热量最高的菜品
    @Test
    public void groupAndFindMaxCalories() {
        Map<Dish.Type, Optional<Dish>> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType,
                        Collectors.maxBy(Comparator.comparing(Dish :: getCalories))));


        result.get(Dish.Type.MEAT).get();
        System.out.println(result.get(Dish.Type.MEAT).get().getCalories());
    }

    //把收集器的结果转换为另一种类型
    @Test
    public void groupAndFindMaxCalories1() {
        Map<Dish.Type, Map<String,Object>> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Dish :: getCalories)), o -> {
                            Map<String,Object> map = new HashMap<>();
                            map.put("name", o.get().getName());
                            map.put("calories", o.get().getCalories());
                            return map;
                        }
                )));

        System.out.println(JsonUtil.toString(result));
    }

    //根据菜单类型分组，统计每组菜单总热量
    @Test
    public void groupAndSumCalories() {
        Map<Dish.Type, Long> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType, Collectors.summingLong(Dish :: getCalories)));

        System.out.println(JsonUtil.toString(result));
    }

    //每种类型的Dish，都有哪些CaloricLevel。
    @Test
    public void groupAndDistinctCalorcLevel() {
        Map<Dish.Type, Set<CaloricLevel>> result = menu.stream()
                .collect(Collectors.groupingBy(Dish :: getType,
                        Collectors.mapping(dish -> {
                            if (dish.getCalories() > 700) {
                                return CaloricLevel.FAT;
                            } else if (dish.getCalories() > 400) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.DIET;
                            }
                        }, Collectors.toSet())));

        System.out.println(JsonUtil.toString(result));
    }



    /*
        分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函
        数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean，于是它最多可以
        分为两组——true是一组，false是一组。分区的好处在于保留了分区函数返回true或false的两套流元素列表。
    */

    //菜单根据是否素食分组
    @Test
    public void vegetarianGroup() {
        Map<Boolean, List<Dish>> result = menu.stream().collect(Collectors.partitioningBy(Dish :: isVegetarian));
        System.out.println(JsonUtil.toString(result));
    }

    //菜单根据是否素食分组 再根据菜单类型分组
    @Test
    public void vegetarianTypeGroup() {
        Map<Boolean, Map<Dish.Type, List<Dish>>> result = menu.stream()
                .collect(Collectors.partitioningBy(Dish :: isVegetarian,
                        Collectors.groupingBy(Dish :: getType)));

        System.out.println(JsonUtil.toString(result));
    }

    //素食和非素食中卡路里最高的食物
    @Test
    public void vegetarianGroupMaxCalories() {
        Map<Boolean, Dish> result = menu.stream()
                .collect(Collectors.partitioningBy(Dish :: isVegetarian,
                        Collectors.collectingAndThen(Collectors.maxBy(
                                Comparator.comparing(Dish::getCalories)), o -> o.get())));

        System.out.println(JsonUtil.toString(result));
    }

    //素食和非素食分组, 卡路里大于500和小于等于500分组
    @Test
    public void demo01() {
        Map<Boolean, Map<Boolean, List<Dish>>> result = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian,
                        Collectors.partitioningBy(dish -> dish.getCalories()>500)));

        System.out.println(JsonUtil.toString(result));
    }

    //质数和非质数分组
    @Test
    public void primeTest() {
        Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, 50)
                .boxed()
                .collect(Collectors.partitioningBy(a -> isPrime(a)));
        System.out.println(JsonUtil.toString(result));
    }

    private boolean isPrime(Integer number) {
        return IntStream.range(2, number/2+1).noneMatch(a -> number%a == 0);
    }

}
