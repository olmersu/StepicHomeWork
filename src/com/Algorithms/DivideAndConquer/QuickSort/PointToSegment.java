package com.Algorithms.DivideAndConquer.QuickSort;

import java.util.Comparator;
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

    interface CompareSegments<T>{
        public int compare(T o1, T o2);
        public int similar(T[] arr, int m);
    }
    class SortedByX1 implements CompareSegments<Segment>{
        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.getx1()-o2.getx1();
        }
        @Override
        public int similar(Segment[] arr, int m) {
            while (m+1 < arr.length && compare(arr[m],arr[m+1])==0){
                m++;
            }
            return m;
        }
    }
    class SortedByX2 implements CompareSegments<Segment>{
        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.getx2()-o2.getx2();
        }
        @Override
        public int similar(Segment[] arr, int m) {
            while (m-1 >= 0 && compare(arr[m],arr[m-1])==0){
                m--;
            }
            return m-1<0?-1:m-1;
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new PointToSegment().run();
        long finishTime = System.currentTimeMillis();
        System.out.println("All time" + (finishTime - startTime) + " ms");
    }

    private void run() {
        Scanner in = new Scanner(System.in);
        int segmentCount = in.nextInt();
        int pointCount = in.nextInt();
        Segment [] segmentArrL = new Segment [segmentCount];
        Segment [] segmentArrR = new Segment [segmentCount];
        int [] pointArr = new int [pointCount];
        for (int i = 0; i < segmentCount; i++) {
            segmentArrL[i] = new Segment(in.nextInt(),in.nextInt());
            segmentArrR[i] = segmentArrL[i];
        }
        for (int i = 0; i < pointCount; i++) {
            pointArr[i]=in.nextInt();
        }

//        int segmentCount = 20;
//        int pointCount = 20;
//        Segment [] segmentArrL = new Segment [segmentCount];
//        Segment [] segmentArrR = new Segment [segmentCount];
//        int [] pointArr = new int [pointCount];
//        Random rand = new Random();
//        Segment temp = new Segment(0,0);
//        for (int i = 0; i < segmentCount; i++) {
//            int x1=rand.nextInt((int) 20);
//            int x2=rand.nextInt((int) 20);
//            if (x1<x2) temp = new Segment(x1,x2);
//            if (x1>x2) temp = new Segment(x2,x1);
//            if (x1==x2) temp = new Segment(x1,x2+5);
//            int tmp = rand.nextInt((int) 20);
////            for (int j = i; j < i+1000; j++) {
//                segmentArrL[i]=temp;
//                System.out.println(temp.getx1()+" "+temp.getx2());
//                segmentArrR[i]=temp;
//                pointArr[i] = tmp;
////            }
//        }
        for (int i = 0; i < pointCount; i++) {
            System.out.println(pointArr[i]);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < pointCount; i++) {

            System.out.print(search(segmentArrL,pointArr[i])+" ");
        }
        System.out.print("\n");
        long finishTime = System.currentTimeMillis();
//        System.out.println("Search time" + (finishTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        quickSorting3(segmentArrL,0,segmentCount-1, new SortedByX1());
        quickSorting3(segmentArrR,0,segmentCount-1, new SortedByX2());
        finishTime = System.currentTimeMillis();
//        System.out.println("QuickSort time" + (finishTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < pointCount; i++) {
            int m = binarySearch(segmentArrL,new Segment(pointArr[i],pointArr[i]),new SortedByX1());
            int k = binarySearch(segmentArrR,new Segment(pointArr[i],pointArr[i]),new SortedByX2());
            System.out.print((m-k)+" ");
        }
        System.out.print("\n");
        finishTime = System.currentTimeMillis();
        System.out.println("search time" + (finishTime - startTime) + " ms");
    }
    private Segment [] quickSorting(Segment [] arr, int l, int r, Comparator<Segment> cmpr){
        if (l>=r) return arr;
        int m = 0;
        while (l<r) {
            m = partition(arr, l, r, cmpr);
            if (m-l>r-m){
                arr = quickSorting(arr, m + 1, r, cmpr);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                r = m - 1;
            }
            else{
                arr = quickSorting(arr, l, m - 1, cmpr);//если левая часть от m больше чем правая, то рекурсию вызываем от меньшей части (от правой)
                l = m + 1;
            }
        }
//        arr = quickSorting(arr, m + 1, r); //элиминирован - вместо этого рекурсивного вызова вызывается один для меньшей части.
        return arr;
    }
    private int partition(Segment [] arr, int l, int r, Comparator<Segment> cmpr){
        Random rand = new Random();
        int m = rand.nextInt(r-l)+l;
        Segment tmp = arr[m];
        arr[m]=arr[l];
        arr[l]=tmp;
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (cmpr.compare(arr[i],arr[l])<0){
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

    private Segment [] quickSorting3(Segment [] arr3, int l, int r, CompareSegments<Segment> cmpr){
        if (l>=r) return arr3;
        int [] m;
        while (l<r) {
            m = partition3(arr3, l, r, cmpr);
            if (m[0]-l>r-m[1]){
                arr3 = quickSorting3(arr3, m[1] + 1, r, cmpr);
                r = m[0] - 1;
            }
            else{
                arr3 = quickSorting3(arr3, l, m[0] - 1, cmpr);
                l = m[1] + 1;
            }
        }
        return arr3;
    }

    private int[] partition3 (Segment [] arr, int l, int r, CompareSegments<Segment> cmpr){
        Random rand = new Random();
        int m = rand.nextInt(r-l)+l;
        Segment tmp = arr[m];
        arr[m]=arr[l];
        arr[l]=tmp;
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if (cmpr.compare(arr[i],arr[l])<=0){
                j++;
                tmp = arr[i];
                arr[i]=arr[j];
                arr[j]=tmp;
            }
        }
        tmp = arr[j];
        arr[j]=arr[l];
        arr[l]=tmp;

        int jm = j;
        for (int i = j-1; i >= l; i--) {
            if (cmpr.compare(arr[i],arr[j])==0){
                jm--;
                tmp = arr[i];
                arr[i]=arr[jm];
                arr[jm]=tmp;
            }
        }
        return new int[] {jm,j};
    }

    private int search(Segment [] arr, int point){
        int j = 0;
        for (int i = 0; i < arr.length ; i++) {
            if (arr[i].getx1()<=point){
                if (arr[i].getx2()>=point){
                    j++;
                }
            }
        }
        return j;
    }

    private int binarySearch(Segment[] arr, Segment key, CompareSegments<Segment> cmpr) {
        int r = arr.length-1;
        int l = 0;
        int m=(l + r) / 2;;
        while (l <= r) {
            m = (l + r) / 2;
            if (cmpr.compare(key,arr[m])==0) return cmpr.similar(arr, m);
            if (cmpr.compare(key,arr[m])<0) r = m - 1;
            if (cmpr.compare(key,arr[m])>0) l = m + 1;
        }
        return cmpr.compare(key,arr[m])<0?m-1:m;
    }

}
