package com.boris.java8.chapter2;

import com.boris.java8.util.JsonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Author: boris
 * @Data: Created on 2019/5/7
 * @Description: Function接收一个对象并映射成另一个对象
 */
public class FunctionDemo {
    @Test
    public void functionTest() {
        //字符串转字符串长度
        List<Integer> result = convertList(Arrays.asList("boris", "lily", "lucy"), name -> name.length());
        System.out.println(JsonUtil.toString(result));
    }

    //两个List中的元素做映射
    public <T, R> List<R> convertList(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
