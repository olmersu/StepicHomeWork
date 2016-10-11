package com.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by bogomolov on 06.10.2016.
 */
public class Generate {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("input.txt");
        int n =100000;
        Random random = new Random();
        printWriter.println(n);
        for (int i = 0; i < n; i++) {
            printWriter.print(random.nextInt() + " ");
        }
        printWriter.close();
    }
}
