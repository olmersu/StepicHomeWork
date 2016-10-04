package com.BasicJava.readasstring;

import com.Test.Timer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by bogomolov on 04.10.2016.
 */
public class ReadAsString {
    public static void main(String[] args) {
        Timer timer = new Timer();
//        long startTime = System.currentTimeMillis();
//        new EndOfStringsConvert().run();
//        long finishTime = System.currentTimeMillis();
        System.out.println(timer.measureTime(() -> {
            try {
                new ReadAsString().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        })+" ms");
    }

    private void run() throws IOException {
        Integer readBuf;
        Integer readBufPrev = null;
        byte[] data = {120, 121, 122, 123};
        System.setIn(new ByteArrayInputStream(data));
        System.out.println(readAsString(System.in, StandardCharsets.US_ASCII));
    }

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,charset));
        return reader.readLine();
    }
}
