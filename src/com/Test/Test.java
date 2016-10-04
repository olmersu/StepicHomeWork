package com.Test;

import sun.nio.cs.US_ASCII;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private void run() throws IOException {

        Writer writer = new OutputStreamWriter(Files.newOutputStream(Paths.get("output.txt")), StandardCharsets.US_ASCII);
        writer.write("Ы");
        writer.flush();
    }
}