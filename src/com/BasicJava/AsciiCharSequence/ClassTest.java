package com.BasicJava.AsciiCharSequence;

/**
 * Created by bogomolov on 19.09.2016.
 */
public class ClassTest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new ClassTest().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run(){

        byte [] b = new byte [25];
        for (byte i = 65; i < 90; i++) {
            b[i-65]=i;
        }
        AsciiCharSequence chStr = new AsciiCharSequence(b);
        System.out.println(chStr);
        System.out.println(chStr.length());
        System.out.println(chStr.charAt(3));
        System.out.println(chStr.subSequence(2,20));
    }
}
