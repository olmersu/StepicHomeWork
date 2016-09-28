package com.BasicJava.Exceptions.Sqrt1;

import java.io.IOException;

/**
 * Created by bogomolov on 26.09.2016.
 */

public class Sqrt1 {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        new Sqrt1().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        System.out.println(sqrt(4));
    }

    public static double sqrt(double x) {
        if (x<0) throw new IllegalArgumentException("Expected non-negative number, got "+x);
        return Math.sqrt(x);
    }
}
