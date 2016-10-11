package com.Algorithms.dynamic_progamming.longest_increasing_subsequence;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by bogomolov on 06.10.2016.
 */
public class Generate {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("input.txt");
        int n =20;
        Random random = new Random();
        printWriter.println(n);
        for (int i = 0; i < n; i++) {
            printWriter.print(random.nextInt((int)20) + " ");
        }
        printWriter.close();
    }
}
