package com.boris.java8.stream;

import com.boris.java8.pojo.Trader;
import com.boris.java8.pojo.Transaction;
import com.boris.java8.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @Author: boris
 * @Data: Created on 2019/5/14
 * @Description: 5.5相关练习
 */
public class TradeDemo {
    List<Transaction> transactions = new ArrayList<>();

    @Before
    public void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //找出2011年的所有交易并按交易额排序（从低到高）
    @Test
    public void test1() {
        List<Transaction> result = transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(comparing(Transaction :: getValue).reversed())
                .collect(toList());
        System.out.println(JsonUtil.toString(result));
    }

    //交易员都在哪些不同的城市工作过
    @Test
    public void test2() {
        List<String> citys = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        System.out.println(JsonUtil.toString(citys));
    }

    //查找所有来自于剑桥的交易员，并按姓名排序
    @Test
    public void test3() {
        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        System.out.println(JsonUtil.toString(traders));
    }

    //返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4() {
        Optional<String> names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(comparing(String::toString).reversed())
                .reduce((a, b) -> a + b + " ");

        System.out.println(names.get());
    }

    //有没有交易员是在米兰工作的
    @Test
    public void test5() {
        boolean result = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println(result);
    }

    //打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6() {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .sorted(comparing(Transaction::getValue))
                .forEach(transaction -> System.out.println(transaction.getValue()));

    }

    //所有交易中，交易额最高最低的交易
    @Test
    public void test7() {
        Optional<Transaction> max = transactions.stream()
                .max(comparing(Transaction::getValue));
        System.out.println(JsonUtil.toString(max.get()));

        Optional<Transaction> min = transactions.stream()
                .min(comparing(Transaction::getValue));
        System.out.println(JsonUtil.toString(min.get()));
    }

    //计算所有交易总和
    @Test
    public void test8() {
//        Optional<Integer> sum = transactions.stream()
//                .map(transaction -> transaction.getValue())
//                .reduce((a, b) -> a+b);
//        System.out.println(sum.get());

        int result = transactions.stream()
                .mapToInt(Transaction :: getValue)
                .sum();
        System.out.println(result);
    }

    //找出名字最长的交易员
    @Test
    public void test9() {
        //如果stream为空 那么min max等结果默认值为0
        //使用orElse可以赋默认值
        OptionalInt result = transactions.stream()
                .mapToInt(transaction -> transaction.getTrader().getName().length())
                .min();
        System.out.println(result.orElse(99));

        Optional<Trader> traderResult = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .min(comparing(trader -> trader.getName().length()));

        System.out.println(JsonUtil.toString(traderResult.get()));
    }
}
