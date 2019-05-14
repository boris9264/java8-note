package com.boris.java8.pojo;

/**
 * @Author: boris
 * @Data: Created on 2019/5/14
 * @Description: 交易信息
 */
public class Transaction {
    private Trader trader;
    private Integer year;
    private Integer value;

    public Transaction(Trader trader, Integer year, Integer value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
