package com.boris.java8.chapter2;

import com.boris.java8.service.BufferReaderProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: boris
 * @Data: Created on 2019/5/7
 * @Description:
 */
public class Demo01 {

    @Test
    public void testRead() throws IOException {
//        String result = this.read((BufferedReader bufferedReader) -> bufferedReader.readLine());
        String result = this.read((BufferedReader bufferedReader) -> bufferedReader.readLine() + "\n" + bufferedReader.readLine());
        System.out.println(result);
    }

    //执行一个行为
    public String read(BufferReaderProcessor bufferReaderProcessor) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\workspace\\text_space\\remark.txt"))) {
            return bufferReaderProcessor.process(bufferedReader);
        }
    }
}
