package com.boris.java8.service;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author: boris
 * @Data: Created on 2019/5/7
 * @Description:
 */

@FunctionalInterface
public interface BufferReaderProcessor {
    //用BufferedReader读取文件中的内容
    String process(BufferedReader br) throws IOException;
}
