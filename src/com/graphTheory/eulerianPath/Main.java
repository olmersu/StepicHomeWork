package com.graphTheory.eulerianPath;


import java.util.*;

/**
 * Created by olmer on 17.04.17.
 */
@SuppressWarnings("Duplicates")
class Solution {
    private int amountOfNodes;
    private int amountOfEdges;

    List<Integer> solution() {
        Map<Integer, Queue<Edge>> connectivityList = setConnectivityList();
        if (amountOfEdges == 0){
            System.out.println("NONE");
            return null;
        }
        List<Integer> path = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();
        Queue<Edge> edges;
        stack.add(1);
        while (!stack.isEmpty()) {
            Integer top = stack.peekLast();
            edges = connectivityList.get(top);
            Edge edge = null;
            while (edges != null && !edges.isEmpty() && (edge = edges.poll()).checked) {
            }
            if ((edge == null || edge.checked) && (edges == null || edges.isEmpty())) {
                top = stack.pollLast();
                path.add(top);
            } else {
                edge.checked();
                Integer nextNode = edge.neighborNode(top);
                stack.add(nextNode);
            }
        }
        if(path.get(0)!=path.get(path.size()-1)){
            System.out.println("NONE");
            return null;
        }
        path.remove(path.size()-1);
        if (path.size() != amountOfEdges) {
            System.out.println("NONE");
            return null;
        }
        path.forEach(s -> System.out.print(s + " "));
        return path;
    }

    //для решения задачи хранилище переделано с Sets на Queue
    // и введен класс ребра Edge
    private Map<Integer, Queue<Edge>> setConnectivityList() {
        Scanner in = new Scanner(System.in);
        amountOfNodes = in.nextInt();
        amountOfEdges = in.nextInt();
        Map<Integer, Queue<Edge>> connectiivityList = new HashMap<>();
        for (int i = 0; i < amountOfEdges; i++) {
            int node1 = in.nextInt();
            int node2 = in.nextInt();
            Edge edge = new Edge(node1, node2);

            if (connectiivityList.containsKey(node1)) {
                connectiivityList.get(node1).add(edge);
            } else {
                Queue<Edge> edges = new LinkedList<>();
                edges.add(edge);
                connectiivityList.put(node1, edges);
            }
            if (connectiivityList.containsKey(node2)) {
                connectiivityList.get(node2).add(edge);
            } else {
                Queue<Edge> edges = new LinkedList<>();
                edges.add(edge);
                connectiivityList.put(node2, edges);
            }
        }
        return connectiivityList;
    }

    class Edge {
        private Integer leftNode;
        private Integer rightNode;
        private Boolean checked;

        Edge(Integer leftNode, Integer rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            checked = false;
        }

        Integer neighborNode(Integer node) {
            return node == leftNode ? rightNode : node == rightNode ? leftNode : null;
        }

        public Boolean isChecked() {
            return checked;
        }

        void checked() {
            checked = true;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Solution().solution();
    }
}