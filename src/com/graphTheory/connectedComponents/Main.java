package com.graphTheory.connectedComponents;

import java.util.*;

/**
 * Created by olmer on 04.04.17.
 */
class Solution {
    int amountOfNodes;
    int amountOfEdges;
    int solution() {
        Map<Integer, Set<Integer>> connectivityList = setConnectivityList();
        boolean[] checkedNodes = new boolean[amountOfNodes];
        int components = 0;
        for (int i = 1; i <= amountOfNodes; i++) {
            if (!checkedNodes[i-1]) {
                    if (connectivityList.containsKey(i)) {
                        nonRecursiveDFS(i, checkedNodes, connectivityList);
                        components++;
                    } else {
                    components++;
                }
            }
        }
        return components;
    }

    private Map<Integer, Set<Integer>> setConnectivityList() {
        Scanner in = new Scanner(System.in);
        amountOfNodes = in.nextInt();
        amountOfEdges = in.nextInt();
        Map<Integer, Set<Integer>> connectiivityList = new HashMap<>();
        for (int i = 0; i < amountOfEdges; i++) {
            int node1 = in.nextInt();
            int node2 = in.nextInt();
            if (connectiivityList.containsKey(node1)) {
                connectiivityList.get(node1).add(node2);
            } else {
                Set<Integer> setInt = new HashSet<>();
                setInt.add(node2);
                connectiivityList.put(node1, setInt);
            }
            if (connectiivityList.containsKey(node2)) {
                connectiivityList.get(node2).add(node1);
            } else {
                Set<Integer> setInt = new HashSet<>();
                setInt.add(node1);
                connectiivityList.put(node2, setInt);
            }
        }
        return connectiivityList;
    }

    int nonRecursiveDFS(int node, boolean[] checkedNodes, Map<Integer, Set<Integer>> connectivityList) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        checkedNodes[node - 1] = true;
        while (!queue.isEmpty()) {
            int nn = queue.poll();
            Set<Integer> setInt = connectivityList.get(nn);
            for (int n : setInt) {
                if (!checkedNodes[n - 1]) {
                    checkedNodes[n - 1] = true;
                    queue.add(n);
                }
            }
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution());
    }
}
