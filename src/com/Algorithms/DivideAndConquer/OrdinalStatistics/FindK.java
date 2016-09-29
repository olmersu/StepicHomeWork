package com.Algorithms.DivideAndConquer.OrdinalStatistics;

import java.util.Random;

/**
 * Created by bogomolov on 29.09.2016.
 * Поиск k-й порядковой статистики.
 * необходимо найти k-й элемент упорядоченного массива.
 * поиск производится с помощью быстройс сортировки
 */
public class FindK {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new FindK().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        int arrSize = 500000;
        int [] arr = new int [arrSize];
        Random rand = new Random();
        for (int i = 0; i < arrSize; i++) {
            arr[i] =rand.nextInt((int) 500000);
//            System.out.print(arr[i]+" ");
        }
        System.out.print("\n");
        long startTime = System.currentTimeMillis();
        System.out.println(partition3(arr,0,arr.length-1,34567));
        long finishTime = System.currentTimeMillis();
        System.out.println("Ordinal statistics" + (finishTime - startTime) + " ms");
    }

    private int partition3 (int [] arr, int l, int r, int k){
        if (l>=r)
            return arr[l];
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
        if (l<=k && k<jm)
            return partition3(arr, l, jm-1, k);
        if (jm<=k && k<=j) return arr[k];
        return partition3(arr, j+1, r, k);
    }
}
