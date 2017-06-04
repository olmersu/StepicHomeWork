package com.Algorithms.generalDataStructure.Tree;

import java.util.*;

/**
 * Created by olmer on 05.05.17.
 */
class Solution {
    public int height(Map<Integer, Set<Integer>> tree, Integer root) {
        int height = 1;
        if (tree.containsKey(root))
            for (int i : tree.get(root)) {
                height = Math.max(height, 1 + height(tree, i));
            }
        return height;
    }
}

@SuppressWarnings("Duplicates")
public class TreeHeight {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amountOfNodes = in.nextInt();
        int root = 0;
        Map<Integer, Set<Integer>> connectiivityList = new HashMap<>();
        for (int i = 0; i < amountOfNodes; i++) {
            int node = in.nextInt();
            if (node == -1) {
                root = i;
                continue;
            }
            if (connectiivityList.containsKey(node)) {
                connectiivityList.get(node).add(i);
            } else {
                Set<Integer> setInt = new HashSet<>();
                setInt.add(i);
                connectiivityList.put(node, setInt);
            }
        }
        System.out.println(new Solution().height(connectiivityList, root));
    }
}
