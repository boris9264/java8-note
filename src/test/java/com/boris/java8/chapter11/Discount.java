package com.boris.java8.chapter11;

/**
 * @Author: boris
 * @Data: Created on 2019/6/17
 * @Description:
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    // Discount类的具体实现这里暂且不表示，参见代码清单11-14
}
