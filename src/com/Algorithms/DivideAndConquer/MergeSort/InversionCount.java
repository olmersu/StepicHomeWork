package com.Algorithms.DivideAndConquer.MergeSort;

import java.util.*;

/**
 * Created by bogomolov on 21.09.2016.
 * Релизация с помощью интерфейса Queue очереди в классе LinkedList
 * Решено с помощьюсотрировки слиянием, но массивы после слияния функцией merge добавляются в массив начиная с первого (не очередь!)
 * суммируется показатель если при сравнении элеменотов массива при слиянии значение из первго массива БОЛЬШЕ второго.
 */

/*
5
2 3 9 2 9

9
9 8 7 6 5 4 3 2 1

5
1 0 1 0 1

1
1

9
1 2 3 4 5 6 7 8 9

9
1 2 4 3 9 5 6 3 7
*/

public class InversionCount {
    private long count=0; // если поставить int то не проходит тест №6 видимо происходит переполнение.
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new InversionCount().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        Queue<int []> queue1 = new LinkedList<>();
        Queue<int []> queue2 = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        int arrCount = in.nextInt();
//        int arrCount=10000;
        int [] testArr = new int[arrCount];
        for (int i = 0; i < arrCount; i++) {
            int n=in.nextInt();
            queue2.add(new int[] {n});
            testArr[i]=n;
        }
//        Random rand = new Random();
//        for (int i = 0; i < arrCount; i++) {
//            int num = rand.nextInt(10000);
//            queue2.add(new int[] {num});
//            testArr[i]=num;
//        }
        while (queue2.size()>1) {
            queue1.addAll(queue2);
            queue2.clear();
            while(queue1.size()>1){
                queue2.add(merge(queue1.remove(),queue1.remove()));
            }
            if(queue1.size()==1) queue2.add(queue1.remove());
        }
        System.out.println(count);
        System.out.println(testing(testArr));
    }

    private int[] merge(int[] arr1, int[] arr2){
        int i=0;
        int j=0;
        int k=0;
        int [] res = new int [arr1.length+arr2.length];
        while(i<arr1.length && j<arr2.length){
            if (arr1[i]<=arr2[j]){
                res[k]=arr1[i];
                k++;
                i++;
            }
            else{
                count+=arr1.length-i;
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
//Тестирование наивным алгоритмом.
    private int testing (int [] arr) {
        int result = 0;
        if (arr.length < 2) return result;
        for (int i=0;i<arr.length;i++) {
            for (int j=i+1;j<arr.length;j++) {
                if (arr[i] > arr[j]){
                    result++;
                }
            }
        }
        return result;
    }
}
