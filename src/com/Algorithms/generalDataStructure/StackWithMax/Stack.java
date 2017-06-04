package com.Algorithms.generalDataStructure.StackWithMax;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by olmer on 06.05.17.
 */
class Solution {
    private Deque<Integer> stack;
    private Deque<Integer> max;

    public Solution() {
        stack = new LinkedList();
        max = new LinkedList();
    }

    public String pull(String request) {
        String[] req = request.split("\\s");
        if ("push".equals(req[0])) {
            stack.addLast(Integer.valueOf(req[1]));
            max.addLast(Math.max(Integer.valueOf(req[1]), max.peekLast() == null ? 0 : max.peekLast()));
        } else if ("pop".equals(req[0])) {
            max.pollLast();
            stack.pollLast().toString();
        } else if ("max".equals(req[0])) {
            return (max.peekLast().toString());
        }
        return null;
    }

}

public class Stack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        int reqSize = Integer.valueOf(in.nextLine());
        Solution stack = new Solution();
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < reqSize; i++) {
            String s = stack.pull(in.nextLine());
            if (s != null)
                response.add(s);
        }
        System.out.println(response);
    }
}
