package com.Test;

import com.Test.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by olmer on 19.09.16.
 */

public class Test<T> {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> new Test().run())+" ms");
    }

    private void run() {
    }
}