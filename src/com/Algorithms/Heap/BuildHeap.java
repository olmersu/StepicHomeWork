package com.Algorithms.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by olmer on 04.06.17.
 * task 3.1 pdf/statements.pdf
 */
class Solution {
    private List<Operation> operationList;
    private int[] arr;

    public Solution(int[] arr) {
        this.arr = arr;
        operationList = new ArrayList<>();
    }

    void solution() {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
        System.out.println(operationList.size());
        operationList.forEach(System.out::println);
    }

    void siftDown(int i) {
        while (2 * i <= arr.length) {
            int j = 2 * i + 1 < arr.length && arr[i] > arr[2 * i + 1] ? 2 * i + 1 : i;
            j = 2 * i + 2 < arr.length && arr[j] > arr[2 * i + 2] ? 2 * i + 2 : j;
            if (i == j) break;
            int el = arr[i];
            arr[i] = arr[j];
            arr[j] = el;
            operationList.add(new Operation(i, j));
            i = j;
        }
    }
}

class Operation {
    private int index1;
    private int index2;

    public Operation(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }

    @Override
    public String toString() {
        return index1 + " " + index2;
    }
}

public class BuildHeap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arrSize = in.nextInt();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = in.nextInt();
        }
        new Solution(arr).solution();
    }
}
