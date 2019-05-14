package com.boris.java8.stream;

import com.boris.java8.pojo.Dish;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
    //sorted 排序 (reversed逆向排序)
    //map 根据dish做转换
    //skip 跳过指定元素
    //limit 数量限制 (skip与limit可结合使用实现分页)
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
                        .skip(1)
                        .limit(2)
//                        .count()
                        .collect(toList());

        System.out.println(JsonUtil.toString(dishs));
    }

    //map可多次使用 demo中 第一次map转化为Stream<String> 第二次转化为Stream<Integer>
    @Test
    public void mapTest() {
        List<Integer> nameLengthList =
                menu.stream()
                        .map(Dish::getName)
                        .map(String::length)
                        .collect(toList());

        System.out.println(JsonUtil.toString(nameLengthList));
    }

    @Test
    public void test() {
        Function<Dish, String> function = this.getFunction();
        List<String> list =
                menu.stream().map(function).collect(toList());

        System.out.println(JsonUtil.toString(list));

        Optional<String> option =
                menu.stream().map(function).findAny();

        System.out.println(option.get());
    }

    private Function<Dish, String> getFunction() {
        return dish -> dish.getName().substring(0,1);
    }
}
