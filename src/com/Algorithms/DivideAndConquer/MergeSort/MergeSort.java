package com.Algorithms.DivideAndConquer.MergeSort;

/**
 * Created by bogomolov on 21.09.2016.
 */


import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bogomolov on 21.09.2016.
 * Релизация сортировки слиянием с помощью интерфейса Queue очереди в классе LinkedList
 */

/*
5
2 3 9 2 9
*/

public class MergeSort {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new MergeSort().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        Queue<int []> queue = new LinkedList<int []>();
        Scanner in = new Scanner(System.in);
        int arrCount = in.nextInt();
        for (int i = 0; i < arrCount; i++) {
            queue.add(new int[] {in.nextInt()});
        }
        while(queue.size()>1){
            queue.add(merge(queue.remove(),queue.remove()));
        }
        int [] arr = queue.remove();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("\n");
    }

    private int[] merge(int[] arr1, int[] arr2){
        int i=0;
        int j=0;
        int k=0;
        int [] res = new int [arr1.length+arr2.length];
        while(i<arr1.length && j<arr2.length){
            if (arr1[i]<arr2[j]){
                res[k]=arr1[i];
                k++;
                i++;
            }
            else{
                res[k]=arr2[j];
                k++;
                j++;
            }
        }
        if (i<arr1.length){
            for(;i<arr1.length;i++){
                res[k]=arr1[i];
                k++;
            }
        }
        if (j<arr2.length){
            for(;j<arr2.length;j++){
                res[k]=arr2[j];
                k++;
            }
        }
        return res;
    }
}
