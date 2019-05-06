package com.boris.java8.pojo;

/**
 * @Author: boris
 * @Data: Created on 2019/5/6
 * @Description:
 */
public class Apple {
    private String color;

    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
