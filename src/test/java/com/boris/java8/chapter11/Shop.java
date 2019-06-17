package com.boris.java8.chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Author: boris
 * @Data: Created on 2019/6/17
 * @Description:
 */
public class Shop {


    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public Shop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return this.calculatePrice(product);
    }




    public Future<Double> getPriceAsync(String product) {
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(
//                () -> {
//                    try {
//                        double price = this.calculatePrice(product);
//                        futurePrice.complete(price);
//                    } catch (Exception e) {
//                        futurePrice.completeExceptionally(e);
//                    }
//                }
//        ).start();

//        return futurePrice;

        //包含了适当得错误管理机制
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
