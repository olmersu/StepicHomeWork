package com.Algorithms.DivideAndConquer.BinarySearch;

/**
 * Created by bogomolov on 16.09.2016.
 * Задача на программирование: двоичный поиск
 * В первой строке даны целое число 1≤n≤10^5
 * и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9, в порядке возрастания, во второй — целое число 1≤k≤10^5
 * и k натуральных чисел b1,…,bk, не превышающих 10^9. Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n,
 * для которого A[j]=bi, или −1, если такого j нет.
 * Sample Input:
 * 5 1 5 8 12 13
 * 5 8 1 23 1 11
 * Sample Output:
 * 3 1 -1 1 -1
 */
/*
5 1 5 8 12 13
5 8 1 23 1 11
*/


import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new BinarySearch().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        Scanner in = new Scanner(System.in);
        int arrCount = in.nextInt();
        int arr[] = new int[arrCount];
        for (int i = 0; i < arrCount; i++) {
            arr[i] = in.nextInt();
        }
        int keyCount = in.nextInt();
        int keyArr[] = new int[keyCount];
        for (int i = 0; i < keyCount; i++) {
            keyArr[i] = in.nextInt();
        }
        for (int i = 0; i < keyCount; i++) {
            System.out.print(binarySearch(arr, keyArr[i]) + " ");
        }
        System.out.print("\n");
    }

    private int binarySearch(int[] arr, int key) {
        int r = arr.length;
        int l = 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (key == arr[m - 1]) return m;
            if (key < arr[m - 1]) r = m - 1;
            if (key > arr[m - 1]) l = m + 1;
        }
        return -1;
    }
}
