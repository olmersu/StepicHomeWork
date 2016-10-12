package com.Algorithms.edit_distance;
/**
 * Created by bogomolov on 12.10.2016.
 */

import com.Test.Timer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EditDistance {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new EditDistance().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }) + " ms");
    }

    private void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] first = in.readLine().toCharArray();
        char[] second = in.readLine().toCharArray();
        if (first.length < second.length) {
            char[] third = first;
            first = second;
            second = third;
        }
        int[] currDistance = new int[first.length+1];
        int[] prevDistance = new int[first.length+1];
        for (int i = 0; i < prevDistance.length; i++) {
            prevDistance[i] = i;
        }
        currDistance[0]=1;
        for (int i = 1; i < second.length+1; i++) {
            for (int j = 1; j < first.length+1; j++) {
                int c = first[j-1]==second[i-1]?0:1;
                currDistance[j]=Math.min(Math.min(prevDistance[j]+1,currDistance[j-1]+1),prevDistance[j-1]+c);
            }
            prevDistance=Arrays.copyOf(currDistance,currDistance.length);
        }
        System.out.println(currDistance[currDistance.length-1]);
    }

}

