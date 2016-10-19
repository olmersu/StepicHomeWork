package com.Algorithms.dynamic_progamming.edit_distance;
/**
 * Created by bogomolov on 12.10.2016.
 */

import com.Test.Timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        String first = in.readLine();
        String second = in.readLine();
        Integer[] currDistance = new Integer[second.length() + 1];
        Integer[] prevDistance = new Integer[second.length() + 1];
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (i == 0 && j == 0) currDistance[j] = 0;
                else {
                    int res = Integer.MAX_VALUE;
                    if (j > 0)
                        res = Math.min(res, currDistance[j - 1] + 1);
                    if (i > 0)
                        res = Math.min(res, prevDistance[j] + 1);
                    if (i > 0 && j > 0)
                        res = Math.min(res, prevDistance[j - 1] + (first.charAt(i - 1) == second.charAt(j - 1) ? 0 : 1));
                    currDistance[j] = res;
                }
            }
            Integer[] temp = currDistance;
            currDistance = prevDistance;
            prevDistance = temp;

        }
        System.out.println(prevDistance[prevDistance.length - 1]);
    }
}

