package com.boris.java8.chapter12;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

/**
 * @Author: boris
 * @Data: Created on 2019/6/18
 * @Description:
 */
public class LocalDateTest {
    @Test
    public void test() {
//        LocalDate date = LocalDate.of(2019, 6, 18);
        LocalDate date = LocalDate.parse("2019-06-18");

        System.out.println(LocalDate.now());
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfWeek());
        System.out.println(date.lengthOfMonth());
        System.out.println(date.isLeapYear());
    }

    @Test
    public void test1() {
//        LocalTime time = LocalTime.of(15, 21, 9);
        LocalTime time =  LocalTime.parse("15:21:09");
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    @Test
    public void test2() {
        LocalDate date = LocalDate.parse("2019-06-18");
        LocalTime time =  LocalTime.parse("15:21:09");
        LocalDateTime dateTime = LocalDateTime.of(2019, 6, 18, 15, 20, 9);
        dateTime = LocalDateTime.of(date, time);

        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
    }

    //有一个LocalDate对象后，生成一个修改版本
    @Test
    public void test3() {
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        System.out.println(date1);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
    }
}
