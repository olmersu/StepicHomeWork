package com.BasicJava.readasdouble;

/**
 * Created by bogomolov on 05.10.2016.
 */
import com.Test.Timer;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ReadAsDouble {
    public static void main(String[] args) {
//        Timer timer = new Timer();
//        System.out.println(timer.measureTime(() -> {
            try {
                new ReadAsDouble().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        })+" ms");
    }

    private void run() throws IOException {
        System.setIn(new StringBufferInputStream("-1e3\n" +  "18 .111 11bbb"));
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        Double sum = new Double(0);
        while (scanner.hasNext()){
            if(scanner.hasNextDouble())
                sum+=scanner.nextDouble();
            else
                scanner.next();
        }
        System.out.format("%.6f%n", sum);
    }
}
