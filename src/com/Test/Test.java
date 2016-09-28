package com.Test;

import java.io.IOException;

/**
 * Created by olmer on 19.09.16.
 */

public class Test {
    class A {

        int a=10;

        public void sayhello() {
            System.out.println("class A");
        }
    }

    class B extends A {

        int a=20;

        public void sayhello() {
            System.out.println("class B");
        }

    }
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        new Test().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        System.out.println(ClassA.class.getName());
    }
    }

//java.lang.ClassCastException