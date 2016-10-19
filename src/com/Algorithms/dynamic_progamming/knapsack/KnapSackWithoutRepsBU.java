package com.Algorithms.dynamic_progamming.knapsack;

import com.Test.Timer;

/**
 * Created by bogomolov on 19.10.2016.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class KnapSackWithoutRepsBU {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new KnapSackWithoutRepsBU().run();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }) + " ms");
    }

    private void run() throws FileNotFoundException {
//        Scanner in = new Scanner(new FileReader("input.txt"));
        Scanner in = new Scanner(System.in);
        int knapsackW = in.nextInt();
        int n = in.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
        }
        System.out.println(knapsackW + " " + n);
        Arrays.stream(w).forEach(x -> System.out.print(x + " "));
        System.out.println();
        int[][] d = new int[knapsackW + 1][n + 1];
        for (int i = 0; i <= knapsackW; i++)
            d[i][0] = 0;
        Arrays.fill(d[0], 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= knapsackW; j++) {
                d[j][i] = d[j][i - 1];
                if (w[i-1] <= j)
                    d[j][i] = Math.max(d[j][i], d[j - w[i-1]][i - 1] + w[i-1]);
            }
        }
        System.out.println(d[knapsackW][n]);
    }
}
