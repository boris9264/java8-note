package com.boris.java8.pojo;

/**
 * @Author: boris
 * @Data: Created on 2019/5/6
 * @Description:
 */
public class Apple {
    private String color;

    private Integer weight;

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
