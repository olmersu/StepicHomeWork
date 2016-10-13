package com.BasicJava.Streams;

import com.Test.Timer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bogomolov on 13.10.2016.
 */
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
        Scanner in = new Scanner(System.in);
        BufferedReader in1 = new BufferedReader(new FileReader("input.txt"));
        in1.lines()
                .flatMap(x -> Stream.of(x.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((x, y) -> Long.compare((-1) * x.getValue(), (-1) * y.getValue()))
                .limit(10)
                .forEach(x -> System.out.println(x.getKey() + " " + x.getValue()));

    }
}
