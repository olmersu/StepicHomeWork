package com.Algorithms.DivideAndConquer.countsort;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by bogomolov on 30.09.2016.
 */
public class CountSort {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        new CountSort().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
//        Scanner in = new Scanner(System.in);
//        int arrCount = in.nextInt();
        int maxNum = 10;
        int arrCount = 10000;
        int[] arr = new int[arrCount];
//        for (int i = 0; i < arrCount; i++) {
//            arr[i] = in.nextInt();
//        }
            Random rand = new Random();
            for (int i = 0; i < arrCount; i++) {
                int num = rand.nextInt(maxNum);
                arr[i] = num;
//                System.out.print(arr[i] + " ");
            }
//            System.out.println("\n");
            arr = sort(arr, maxNum);
//            for (int i = 0; i < arrCount; i++) {
//                System.out.print(arr[i] + " ");
//            }
//            System.out.println("\n");
        }
        private int[] sort ( int[] arr, int maxNum){
            int[] resArr = new int[arr.length];
            int[] arrB = new int[maxNum];
            Arrays.fill(arrB, 0);
            for (int i = 0; i < arr.length; i++) {
                arrB[arr[i]] += 1;
            }
            for (int i = 1; i < maxNum; i++) {
                arrB[i] += (arrB[i - 1]);
            }
            for (int i = arr.length - 1; i >= 0; i--) {
                resArr[arrB[arr[i]] - 1] = arr[i];
                arrB[arr[i]] -= 1;
            }
            return resArr;
        }
    }
