package com.graphTheory.distanceBetweenNodes;

import java.util.*;

/**
 * Created by olmer on 12.04.17.
 */
class Solution {
    int amountOfNodes;
    int amountOfEdges;
    int solution() {
        Map<Integer, Set<Integer>> connectivityList = setConnectivityList();
        boolean[] checkedNodes = new boolean[amountOfNodes];
        int [] distances = new int [amountOfNodes];
        int components = 0;
        for (int i = 0; i < amountOfNodes; i++) {
            if (!checkedNodes[i]) {
                    if (connectivityList.containsKey(i)) {
                        nonRecursiveDFS(i, checkedNodes, connectivityList, distances);
                        components++;
                    } else {
                    components++;
                }
            }
        }
        Arrays.stream(distances)
                .forEach(s->System.out.print(s+" "));
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

    int nonRecursiveDFS(int node, boolean[] checkedNodes, Map<Integer, Set<Integer>> connectivityList, int[] distances) {
        int delimeter = Integer.MAX_VALUE;
        int level = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        checkedNodes[node] = true;
        distances[node]=level;
        level ++;
        queue.add(delimeter);
        while (!queue.isEmpty()) {
            int nn = queue.poll();
            if (nn == delimeter) {
                level++;
                if (!queue.isEmpty()) {
                    queue.add(delimeter);
                }
            }
            else {
                Set<Integer> setInt = connectivityList.get(nn);
                for (int n : setInt) {
                    if (!checkedNodes[n]) {
                        checkedNodes[n] = true;
                        distances[n]=level;
                        queue.add(n);
                    }
                }
            }
        }
        return 0;
    }
}

public class Main {
    public static void main(String[] args) {
        new Solution().solution();
    }
}
