package com.Algorithms.generalDataStructure.MaxInWindow;

import java.util.*;

/**
 * Created by olmer on 10.05.17.
 */
class Solution {
    int[] solution(int[] arr, int windowSize) {
        Deque<Pair> inStack = new LinkedList<>();
        Deque<Pair> outStack = new LinkedList<>();
        int[] localMax = new int[arr.length - windowSize + 1];
        int i = 0;
        for (int a : arr) {
            inStack.add(new Pair(a, Math.max(a, inStack.peekLast() == null ? Integer.MIN_VALUE : inStack.peekLast().getLocalMax())));
            if (outStack.size() + inStack.size() >= windowSize) {
                if (outStack.isEmpty())
                    moveStack(inStack, outStack);
                localMax[i] = Math.max(outStack.pollLast().getLocalMax(), inStack.peekLast() == null ? Integer.MIN_VALUE : inStack.peekLast().getLocalMax());
                i++;
            }
        }
        return localMax;
    }

    private void moveStack(Deque<Pair> inStack, Deque<Pair> outStack) {
        while (!inStack.isEmpty()) {
            Pair a = inStack.pollLast();
            outStack.add(new Pair(a.getNumber(), Math.max(a.getNumber(), outStack.peekLast() == null ? Integer.MIN_VALUE : outStack.peekLast().getLocalMax())));
        }
    }
}

class Pair {
    private Integer number;
    private Integer localMax;

    public Pair(Integer number, Integer localMax) {
        this.number = number;
        this.localMax = localMax;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getLocalMax() {
        return localMax;
    }

    public void setLocalMax(Integer localMax) {
        this.localMax = localMax;
    }
}

public class MaxInWindow {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arrSize = in.nextInt();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = in.nextInt();
        }
        int windowSize = in.nextInt();
        Arrays.stream(new Solution().solution(arr, windowSize)).forEach(s->System.out.print(s+" "));
    }
}
