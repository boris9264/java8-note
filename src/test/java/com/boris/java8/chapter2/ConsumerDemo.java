package com.boris.java8.chapter2;

import com.boris.java8.pojo.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: boris
 * @Data: Created on 2019/5/7
 * @Description:
 */
public class ConsumerDemo {
    List<Apple> inventory = new ArrayList<>();
    @Before
    public void initInventory() {
        Apple apple = new Apple("green", 130);
        inventory.add(apple);

        Apple apple11 = new Apple("red", 120);
        inventory.add(apple11);

        inventory.add(new Apple("red", 190));
    }

    //Consumer接收一个对象，操作这个对象中的值，返回值为void
    @Test
    public void testConsumer() {
        print(Arrays.asList(1,2,3,4,5,10), i-> System.out.println(i));
        print(inventory, apple -> System.out.println(apple.getColor()));
    }

    public <T> void print(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
