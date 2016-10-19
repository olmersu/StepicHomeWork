package com.BasicJava.Streams;
/**
 * Created by bogomolov on 13.10.2016.
 */


import com.Test.Timer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindSimilarWords {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new FindSimilarWords().run();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }) + " ms");
    }

    private void run() throws FileNotFoundException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.lines()
                .flatMap(x -> Stream.of(x.split("[^а-яА-Яa-zA-Z0-9]+")))
                .map(String::toLowerCase)
//                .peek(System.out::println)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2)->{
                    if(Long.compare((-1)*o1.getValue(),(-1)*o2.getValue()) == 0){
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    else {
                        return Long.compare((-1) * o1.getValue(), (-1) * o2.getValue());
                    }
                })
                .limit(10)
//                .forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));
                .forEach(x -> System.out.println(x.getKey()));
    }
}
