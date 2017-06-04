package com.Algorithms.generalDataStructure.Stack;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by olmer on 01.05.17.
 */
class Solution {
    public String solution(String brackets) {
        List<Character> stack = new LinkedList<>();
        List<Integer> stackNumbers = new LinkedList<>();
        Integer i = 0;
        for (char b : brackets.toCharArray()) {
            i++;
            if (b == '(' || b == '[' || b == '{') {
                switch (b) {
                    case '(':
                        stack.add(')');
                        break;
                    case '[':
                        stack.add(']');
                        break;
                    case '{':
                        stack.add('}');
                        break;
                }
                stackNumbers.add(i);
            }
            if (b == ')' || b == ']' || b == '}') {
                if (stack.isEmpty())
                    return i.toString();
                stackNumbers.remove(stackNumbers.size() - 1);
                if (b != stack.remove(stack.size() - 1))
                    return i.toString();
            }
        }
        if (stack.size() > 0)
            return stackNumbers.remove(stackNumbers.size()-1).toString();
        return "Success";
    }
}

public class Brackets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String brackets = in.nextLine();
        System.out.println(new Solution().solution(brackets));
    }
}
