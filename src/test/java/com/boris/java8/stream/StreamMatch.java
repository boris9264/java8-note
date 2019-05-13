package com.boris.java8.stream;

import com.boris.java8.pojo.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: boris
 * @Data: Created on 2019/5/13
 * @Description:
 */
public class StreamMatch {
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

    //判断菜单中是否包含素菜
    @Test
    public void anyMatchTest() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("菜单中包含素菜.");
        }
    }

    //findFirst和findAny findFirst是为并行(parallelStream)准备的 保证顺序

/*    短路求值
    有些操作不需要处理整个流就能得到结果。例如，假设你需要对一个用 and 连起来的大布
    尔表达式求值。不管表达式有多长，你只需找到一个表达式为 false ，就可以推断整个表达式
    将返回 false ，所以用不着计算整个表达式。这就是 短路。
    对于流而言，某些操作（例如 allMatch 、 anyMatch 、 noneMatch 、 findFirst 和 findAny ）
    不用处理整个流就能得到结果。只要找到一个元素，就可以有结果了。同样， limit 也是一个
    短路操作：它只需要创建一个给定大小的流，而用不着处理流中所有的元素。在碰到无限大小
    的流的时候，这种操作就有用了：它们可以把无限流变成有限流。我们会在5.7节中介绍无限
    流的例子。*/
}
