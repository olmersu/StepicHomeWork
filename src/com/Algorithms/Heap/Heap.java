package com.Algorithms.Heap;

/**
 * Created by bogomolov on 14.09.2016.
 * Задача на программирование: очередь с приоритетами
 * Первая строка входа содержит число операций 1≤n≤10^5.
 * Каждая из последующих n строк задают операцию одного из следующих двух типов:
 *  - Insert x, где 0≤x≤10^9 — целое число;
 *  - ExtractMax
 *  Первая операция добавляет число x
 *  в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 *  Sample Input:
 *  6
 *  Insert 200
 *  Insert 10
 *  ExtractMax
 *  Insert 5
 *  Insert 500
 *  ExtractMax
 *  Sample Output:
 *  200
 *  500
 */
/*
15
Insert 200
Insert 10
ExtractMax
Insert 5
Insert 500
ExtractMax
Insert 3
Insert 100
Insert 450
Insert 560
Insert 200
Insert 800
ExtractMax
ExtractMax
ExtractMax
*/
import java.util.Scanner;
import java.util.Vector;

public class Heap {
    private static Vector<Integer> heap = new Vector<>();
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int stepsAmount = in.nextInt();
        for (int i=0;i<stepsAmount;i++){
            String s = in.next();
            String [] sParts = s.split("\\s");
            if (sParts[0].equals("Insert")) {
                insert((Integer.parseInt(sParts[1])));
            }
            if (sParts[0].equals("ExtractMax")) {
                extractMax();
            }
        }
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime-startTime+" ms");
    }
    private static void insert(int x){
        heap.add(x);
        int i=heap.size();
        if(i>1) {
            while (i > 1 && x >= heap.get(i / 2 - 1)) {
                heap.set(i - 1, heap.set(i / 2 - 1, x));
                i = i / 2;
            }
        }
    }
    private static void extractMax(){
        int m = heap.get(0);
        heap.set(0, heap.lastElement());
        heap.removeElementAt(heap.size() - 1);
        int i = 1;
        int n = heap.size();
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
        System.out.println(m);
    }
}
