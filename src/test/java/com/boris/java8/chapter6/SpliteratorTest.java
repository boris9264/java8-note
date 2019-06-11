package com.boris.java8.chapter6;

import org.junit.Before;
import org.junit.Test;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @Author: boris
 * @Data: Created on 2019/6/11
 * @Description:
 */
public class SpliteratorTest {
    String SENTENCE = "";
    @Before
    public void init() {
        SENTENCE = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
    }

    @Test
    public void test1() {
        Stream<Character> characterStream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        int result = this.countWords(characterStream.parallel());
        System.out.println(result);
    }

    @Test
    public void test2() {
        Spliterator spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> characterStream = StreamSupport.stream(spliterator, true);
        int result = this.countWords(characterStream);
        System.out.println(result);
    }

    private int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                                                WordCounter::accumulate,
                                                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
