package com.boris.java8.service;

/**
 * @Author: boris
 * @Data: Created on 2019/6/12
 * @Description: default method demo
 */
public interface Resizable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);

    default void outPrint() {
        getWidth();
        getHeight();
    }
}
