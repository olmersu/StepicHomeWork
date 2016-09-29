package com.Algorithms.DivideAndConquer.HeapSort;

import java.util.Random;
import java.util.Vector;

/**
 * Created by bogomolov on 29.09.2016.
 */
public class HeapSort {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new HeapSort().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        int arrSize = 500000;
        int[] arr = new int[arrSize];
        Random rand = new Random();
        for (int i = 0; i < arrSize; i++) {
            arr[i] = rand.nextInt((int) 500000);
//            System.out.print(arr[i]+" ");
        }
        System.out.print("\n");
        long startTime = System.currentTimeMillis();
//            System.out.println(partition3(arr,0,arr.length-1,34567));
        long finishTime = System.currentTimeMillis();
        System.out.println("Ordinal statistics" + (finishTime - startTime) + " ms");
    }

    private void siftDown(Vector<Integer> heap, int k){
        int i = k+1;
        int n = heap.size();
        int j = i;
        while (2*i<=n) {
            int el = heap.get(j-1);
            if (heap.get(2 * i-1) < el) {
                j = 2 * i;
            }
            if (2*i+1<=n && heap.get(2*i+1-1) < heap.get(j-1)) {
                j = 2 * i + 1;
            }
            if (i==j) break;
            heap.set(i-1,heap.set(j-1,el));
            i=j;
        }
    }
}
