package com.boris.java8.stream;

import com.boris.java8.pojo.CaloricLevel;
import com.boris.java8.pojo.Dish;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
