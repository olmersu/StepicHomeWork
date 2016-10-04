package com.Test;

/**
 * Created by bogomolov on 04.10.2016.
 */
public class Timer {
    public long measureTime(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - startTime;
    }
}
