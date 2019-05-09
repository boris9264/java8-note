package com.boris.java8.pojo;

/**
 * @Author: boris
 * @Data: Created on 2019/5/9
 * @Description: 菜
 */
public class Dish {

    private String name;
    //卡路里 热量
    private Integer calories;

    //是否素食
    private boolean vegetarian;

    private Type type;

    public Dish(String name, Integer calories, boolean vegetarian, Type type) {
        this.name = name;
        this.calories = calories;
        this.vegetarian = vegetarian;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        MEAT, FISH, OTHER
    }
}
