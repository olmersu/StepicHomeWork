package com.BasicJava.Streams;

import com.Test.Test;
import com.Test.Timer;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogomolov on 13.10.2016.
 */
public class PseudoRandomStream {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> new PseudoRandomStream().run())+" ms");
    }

    private void run() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        pseudoRandomStream(num).forEach(x->System.out.printf("%d ", x));
        //System.out.println(mid(num*num));
    }

    private IntStream pseudoRandomStream(int num) {
        return IntStream.iterate(num, x -> {
            int n=x*x;
            int n2 = n/10%10;
            int n3 = n/100%10;
            int n4 = n/1000%10;
            return n2+n3*10+n4*100;
        });
    }
}
