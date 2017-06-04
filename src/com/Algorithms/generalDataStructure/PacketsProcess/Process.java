package com.Algorithms.generalDataStructure.PacketsProcess;

import java.util.*;

/**
 * Created by olmer on 05.05.17.
 */
class Solution {
    int[] solution(int bufferSize, int[] arrival, int[] duration) {
        Deque<Integer> buffer = new LinkedList<>();
        int[] process = new int[arrival.length];
        int i = 0;
        int currentTime = 0;
        while (i < arrival.length && buffer.size() < bufferSize) {
            buffer.add(i);
            i++;
        }
        while (!buffer.isEmpty()) {
            int packet = buffer.poll();
            process[packet] = Math.max(currentTime, arrival[packet]);
            currentTime = Math.max(currentTime, arrival[packet]) + duration[packet];
            while (i < arrival.length && buffer.size() < bufferSize) {
                if (arrival[i] >= currentTime)
                    buffer.add(i);
                else
                    process[i] = -1;
                i++;
            }
        }
        return process;
    }
}

public class Process {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int bufferSize = in.nextInt();
        int packetNum = in.nextInt();
        int[] arrival = new int[packetNum];
        int[] duration = new int[packetNum];
        for (int i = 0; i < packetNum; i++) {
            arrival[i] = in.nextInt();
            duration[i] = in.nextInt();
        }
        Arrays.stream(new Solution().solution(bufferSize, arrival, duration)).forEach(System.out::println);
    }
}
