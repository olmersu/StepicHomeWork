package com.Test;

import com.Test.Timer;

/**
 * Created by olmer on 19.09.16.
 */

public class Test {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> new Test().run())+" ms");
    }

    private void run() {
        System.out.println((int)2e9);
    }
}