package com.Algorithms.DivideAndConquer.HeapSort;

import java.util.*;

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
        int arrSize = 50000000;
        Integer [] arr = new Integer[arrSize];
        Random rand = new Random();
        for (int i = 0; i < arrSize; i++) {
            arr[i] = rand.nextInt((int) 50000000);
//            System.out.print(arr[i]+" ");
        }
//        System.out.print("\n");
        ArrayList<Integer> heap = new ArrayList<Integer>(Arrays.asList(arr));
        long startTime = System.currentTimeMillis();
        heapSort(heap);
        long finishTime = System.currentTimeMillis();
        System.out.println("Heap sort" + (finishTime - startTime) + " ms");
    }

    private ArrayList<Integer> heapSort(ArrayList<Integer> heap){
        heap = buildMaxHeap(heap);
        int size = heap.size();
        int n = size;
        for (int i = n-1; i > 0 ; i--) {
            Integer temp = heap.get(size-1);
            heap.set(size-1,heap.set(0,temp));
            size=size-1;
            siftDown(heap,0,size);
        }
//        for (Integer i :
//                heap) {
//            System.out.print(i+" ");
//        }
//        System.out.print("\n");
        return heap;
    }

    private ArrayList<Integer> buildMaxHeap(ArrayList<Integer> heap){
        for (int i = (heap.size()-1)/2; i >= 0 ; i--) {
            siftDown(heap, i, heap.size());
        }
//        for (Integer i :
//                heap) {
//            System.out.print(i+" ");
//        }
//        System.out.print("\n");
        return heap;
    }

    private ArrayList<Integer> siftDown(ArrayList<Integer> heap, int k, int size){
        int i = k+1;
        int n = size;
        int j = i;
        while (2*i<=n) {
            int el = heap.get(j-1);
            if (heap.get(2 * i-1) > el) {
                j = 2 * i;
            }
            if (2*i+1<=n && heap.get(2*i+1-1) > heap.get(j-1)) {
                j = 2 * i + 1;
            }
            if (i==j) break;
            heap.set(i-1,heap.set(j-1,el));
            i=j;
        }
        return heap;
    }
}
