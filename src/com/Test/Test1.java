package com.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by olmer on 16.05.17.
 */
public class Test1 {
    public static void main(String[] args) {
        Deque<Integer> inStack = new LinkedList<>();
        System.out.println(Math.max(inStack.peekLast() == null ? Integer.MIN_VALUE : inStack.peekLast(), 3));
    }
}
