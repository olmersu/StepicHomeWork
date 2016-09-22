package com.Algorithms.DivideAndConquer.QuickSort;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by bogomolov on 22.09.2016.
 */

/*
2 3
0 5
7 10
1 6 11
*/
//10 010 ms

public class PointToSegment {
    class Segment {
        int x1;
        int x2;
        public Segment(int x1, int x2){
            this.x1=x1;
            this.x2=x2;
        }
        public int getx1(){
            return this.x1;
        }
        public int getx2(){
            return this.x2;
        }
        public void setx1(int x1){
            this.x1=x1;
        }
        public void setx2(int x2){
            this.x2=x2;
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new PointToSegment().testrun();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void testrun(){
        int [] arr ={1,3,5,7,9,11,13,14,15};
        System.out.println(binarySearch1(arr,4));
        System.out.println(binarySearch1(arr,10));
        System.out.println(binarySearch1(arr,16));
        System.out.println(binarySearch1(arr,2));
        System.out.println(binarySearch1(arr,13));
    }


    private void run() {
//        Scanner in = new Scanner(System.in);
//        int segmentCount = in.nextInt();
//        int pointCount = in.nextInt();
//        Segment [] segmentArr = new Segment [segmentCount];
//        int [] pointArr = new int [pointCount];
//        for (int i = 0; i < segmentCount; i++) {
//            segmentArr[i] = new Segment(in.nextInt(),in.nextInt());
//        }
//        for (int i = 0; i < pointCount; i++) {
//            pointArr[i]=in.nextInt();
//        }

        int segmentCount = 50000;
        int pointCount = 50000;
        Segment [] segmentArr = new Segment [segmentCount];
        int [] pointArr = new int [pointCount];
        Random rand = new Random();
        for (int i = 0; i < segmentCount; i++) {
            segmentArr[i] = new Segment(rand.nextInt((int) 1e8),rand.nextInt((int) 1e8));
            pointArr[i]=rand.nextInt((int) 1e8);
        }
        for (int i = 0; i < pointCount; i++) {
            search(segmentArr,pointArr[i]);
        }
        QuickSorting(segmentArr,0,segmentCount-1);

    }
    private Segment [] QuickSorting(Segment [] arr, int l, int r){
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
    private int partition(Segment [] arr, int l, int r){
        Random rand = new Random();
        int m = rand.nextInt(r-l)+l;
        Segment tmp = arr[m];
        arr[m]=arr[l];
        arr[l]=tmp;
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (arr[i].getx1()<arr[l].getx1()){
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
    private int search(Segment [] arr, int point){
        int j = 0;
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i].getx1()<point){
                if (arr[i].getx2()>point){
                    j++;
                }
            }
        }
        return j;
    }

    private int binarySearch(Segment[] arr, int key) {
        int r = arr.length;
        int l = 1;
        int m=(l + r) / 2;;
        int mPrev=0;
        while (l <= r) {
            mPrev=m;
            m = (l + r) / 2;
            if (key == arr[m - 1].getx1()) return m;
            if (key < arr[m - 1].getx1()) r = m - 1;
            if (key > arr[m - 1].getx1()) l = m + 1;
        }
        return key<mPrev?m:mPrev;
    }
    private int binarySearch1(int[] arr, int key) {
        int r = arr.length;
        int l = 1;
        int m=(l + r) / 2;;
        int mPrev=0;
        while (l <= r) {
            mPrev=m;
            m = (l + r) / 2;
            if (key == arr[m - 1]) return m;
            if (key < arr[m - 1]) r = m - 1;
            if (key > arr[m - 1]) l = m + 1;
        }
        return key<mPrev?m:mPrev;
    }

}
//        int [] arr ={1,3,5,7,9,11,13,14,15};