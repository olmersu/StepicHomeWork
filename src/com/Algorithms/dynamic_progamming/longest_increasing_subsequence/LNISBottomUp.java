package com.Algorithms.dynamic_progamming.longest_increasing_subsequence;

import com.Test.Timer;

/**
 * Created by bogomolov on 07.10.2016.
 * longest not increasing subsequence
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LNISBottomUp {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new LNISBottomUp().run();
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
        lisBottomUP(arr);
    }

    private void lisBottomUP(int[] arr) {
        int n = arr.length;
        int maxN = 0;
        for (int i = 0; i < n; i++) {
            if (maxN < arr[i]) maxN = arr[i];
        }
        int[][] arrD = new int[2][n];
        arrD[0][0] = maxN+1;
        arrD[1][0] = -1;
        for (int i = 1; i < n; i++) {
            arrD[0][i] = -1;
            arrD[1][i] = -1;
        }
        int [] arrParent = new int[n];
        for (int i = 0; i < n; i++) {
            int j = binarySearchRightBorder(arrD[0], arr[i]);
            if (arrD[0][j-1] >= arr[i] && arr[i] > arrD[0][j]) {
                arrD[0][j] = arr[i];
                arrParent[i] = arrD[1][j-1];
                arrD[1][j] = i;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arrParent[i]+" ");
        }
        System.out.println();
        int k = 0;
        for (k = 1; (k < n)&&(arrD[0][k]<maxN+1); k++) {}
        System.out.println(k-1);
        for (int i = 0; i < n; i++) {
            if (arrD[1][i]!=-1) {
                System.out.println(arrD[1][i]+": "+arrD[0][i] + " ");
            }
        }
        int j = arrD[1][k-1];
        for (int i = k-1; (i > 0)&&(j>=0); i--) {
            System.out.print(j+" ");
            j=arrParent[j];
        }
        System.out.println();
    }

    private int binarySearchLeftBorder(int[] arr, int key) {
        int r = arr.length;
        int l = - 1;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (key >= arr[m]) l = m;
            else r = m;
        }
        return l + 1;
    }
    private int binarySearchRightBorder(int[] arr, int key) {
        int r = arr.length;
        int l = - 1;
        while (l + 1 < r) {
            int m = (l + r) >> 1;
            if (key <= arr[m]) l = m;
            else r = m;
        }
        return r;
    }

}

