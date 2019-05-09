package com.boris.java8.stream;

import com.boris.java8.pojo.Dish;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @Author: boris
 * @Data: Created on 2019/5/9
 * @Description:
 *  1. stream可以并行的处理集合
 *  2. 可以以声明的方式处理集合 类似sql语句
 */
public class DishTest {
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

    //filter 过滤卡路里>400的菜
    //sorted 排序
    //map 根据dish做转换
    //limit 数量限制
    //distinct 去重

    //count 终端操作 总数
    @Test
    public void generateDish() {
        List<String> dishs =
                menu.parallelStream()
                        .filter(dish -> dish.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories).reversed())
                        .map(Dish::getName)
                        .distinct()
                        .limit(4)
//                        .count()
                        .collect(toList());

        System.out.println(JsonUtil.toString(dishs));
    }
}
