package com.Algorithms.DivideAndConquer.QuickSort;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by bogomolov on 22.09.2016.
 * Быстрая сортировка массива с элиминацией последнего рекурсивного вызова.
 * Qucksorting -  простая быстрая сотртировка
 * Qucksorting3 - быстрая сотртировка с делением отрезка сотртировки на 3 части  <x, =x, >x
 */
/*
5
3 3 1 0 2

11 2 7 1 10 15 19 19 17 12 13 11 0 9 12 13 3 11 5 7
*/


public class QuickSort {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new QuickSort().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
//        Scanner in = new Scanner(System.in);
//        int arrCount = in.nextInt();
        int arrCount=20;
        int [] arr = new int [arrCount];
        int [] arr3 = new int [arrCount];
//        for (int i = 0; i < arrCount; i++) {
//            arr[i] = in.nextInt();
//            arr3[i]=arr[i];
//        }
        Random rand = new Random();
        for (int i = 0; i < arrCount; i++) {
            int num = rand.nextInt(arrCount);
            arr[i]=num;
            arr3[i]=num;
            System.out.print(arr[i]+" ");
        }
        System.out.println("\n");
        int l=0, r=arrCount-1;
        QuickSorting(arr,l,r);
        QuickSorting3(arr3,l,r);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(" Quicksort\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr3[i]+" ");
        }
        System.out.println(" QuickSort3\n");
    }

    private int [] QuickSorting(int [] arr, int l, int r){
        if (l>=r) return arr;
        int m = 0;
        while (l<r) {
            m = partition(arr, l, r);
            if (m-l>r-m){
                arr = QuickSorting(arr, m + 1, r);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                r = m - 1;
            }
            else{
                arr = QuickSorting(arr, l, m - 1);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                l = m + 1;
            }
        }
//        arr = QuickSorting(arr, m + 1, r); //элиминирован - вместо этого рекурсивного вызова вызывается один для меньшей части.
        return arr;
    }
    private int partition(int [] arr, int l, int r){
        Random rand = new Random();
        int m = rand.nextInt(r-l)+l;
        int tmp = arr[m];
        arr[m]=arr[l];
        arr[l]=tmp;
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i]<arr[l]){
                j++;
                tmp = arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
            }
        }
            tmp = arr[j];
            arr[j]=arr[l];
            arr[l]=tmp;
        return j;
    }

    private int [] QuickSorting3(int [] arr3, int l, int r){
//сортировка с учётом того, что в массиве должны быть одинаковые элементы, тогда массив разбивается на 3 части. <x, =x, >x
//если известно, что в массиве элементы уникальны, то лучше использовать простой алгоритм
        if (l>=r) return arr3;
        int [] m = {0,0};
        while (l<r) {
            m = partition3(arr3, l, r);
            if (m[0]-l>r-m[1]){
                arr3 = QuickSorting3(arr3, m[1] + 1, r);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                r = m[0] - 1;
            }
            else{
                arr3 = QuickSorting3(arr3, l, m[0] - 1);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                l = m[1] + 1;
            }
        }
//        arr = QuickSorting(arr, m + 1, r); //элиминирован - вместо этого рекурсивного вызова вызывается один для меньшей части.
        return arr3;
    }

    private int[] partition3 (int [] arr, int l, int r){
        Random rand = new Random();
        int m = rand.nextInt(r-l)+l;
        int tmp = arr[m];
        arr[m]=arr[l];
        arr[l]=tmp;
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i]<=arr[l]){
                j++;
                tmp = arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
            }
        }
        tmp = arr[j];
        arr[j]=arr[l];
        arr[l]=tmp;
//Можно все сделать без дополнительного цикла, за один проход, но сложно учитывать одновременно два индекса
        int jm = j;
        for (int i = j-1; i >= l; i--) {
            if (arr[i]==arr[j]){
                jm--;
                tmp = arr[i];
                arr[i]=arr[jm];
                arr[jm]=tmp;
            }
        }
        return new int[] {jm,j};
    }
}