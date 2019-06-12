package com.boris.java8.chapter9;

import com.boris.java8.service.Resizable;

/**
 * @Author: boris
 * @Data: Created on 2019/6/12
 * @Description:
 */
public class Ellipse implements Resizable {
    private int width;
    private int height;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setAbsoluteSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
