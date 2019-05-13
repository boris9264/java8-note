package com.boris.java8.stream;

import com.boris.java8.util.JsonUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author: boris
 * @Data: Created on 2019/5/13
 * @Description:
 */
public class FlatMapDemo {
    //两个数组返回所有数对 [1,3] [1,4] [2,3] [2,4]
    @Test
    public void flatMapTest() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<Integer[]> result = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new Integer[]{i,j}))
                .collect(toList());

        System.out.println(JsonUtil.toString(result));
    }

    //两个数组返回所有能被3整除的数对 [2,4] [3,3]
    @Test
    public void flatMapTest2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<Integer[]> result = numbers1
                .stream()
                .flatMap(i -> numbers2
                                .stream()
                                .filter(j -> (i+j)%3 == 0)
                                .map(j -> new Integer[]{i, j}))
                .collect(toList());

        System.out.println(JsonUtil.toString(result));
    }
}
