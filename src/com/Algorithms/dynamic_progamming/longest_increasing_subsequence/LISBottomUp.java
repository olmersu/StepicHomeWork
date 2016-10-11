package com.Algorithms.dynamic_progamming.longest_increasing_subsequence;

import com.Test.Timer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by bogomolov on 06.10.2016.
 */
public class LISBottomUp {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new LISBottomUp().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }) + " ms");
    }

    private void run() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        int i = 0;
        for (String s : in.readLine().split(" ")) {
            arr[i] = Integer.parseInt(s);
            i++;
        }
        for (i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        int k = lisBottomUP(arr);
        System.out.println(k);
    }

    private int lisBottomUP(int[] arr) {
        int n = arr.length;
        int[] arrD = new int[n];
        for (int i = 0; i < n; i++) {
            arrD[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
//                if (arr[i] > arr[j] && arrD[j] + 1 > arrD[i]) // оригинальное условие при поиске возрастающей последовательности без всяких доп условий.
                if (arr[i] % arr[j] == 0 && arrD[j] + 1 > arrD[i])
                    arrD[i] = arrD[j] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans,arrD[i]);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arrD[i] + " ");
        }
        System.out.println("");
        return ans;
    }
}
