package com.BasicJava.endofstringsconvert;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.Test.Timer;

/**
 * Created by bogomolov on 03.10.2016.
 */
public class EndOfStringsConvert {
    public static void main(String[] args) throws IOException {
        Timer timer = new Timer();
//        long startTime = System.currentTimeMillis();
//        new EndOfStringsConvert().run();
//        long finishTime = System.currentTimeMillis();
        System.out.println(timer.measureTime(() -> {
            try {
                new EndOfStringsConvert().run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        })+" ms");
    }

    private void run() throws IOException {
        Integer readBuf;
        Integer readBufPrev = null;
//        byte[] data = {65, 13, 10, 10, 13};
//        System.setIn(new ByteArrayInputStream(data));
//        System.setOut(new PrintStream(Files.newOutputStream(Paths.get("output.txt"))));
        try (InputStream inputStream = new BufferedInputStream(System.in); OutputStream outputStream = new BufferedOutputStream(System.out)) {
            while ((readBuf = inputStream.read()) > 0){
                if (readBufPrev == null) {
                    readBufPrev = readBuf;
                    continue;
                }
                if (readBufPrev.byteValue() == 13 && readBuf.byteValue() == 10){
                    outputStream.write(readBuf.byteValue());
                    outputStream.flush();
                    readBufPrev = null;
                }
                else{
                    outputStream.write(readBufPrev.byteValue());
                    outputStream.flush();
                    readBufPrev = readBuf;
                }
            }
            if (readBufPrev != null) {
                outputStream.write(readBufPrev.byteValue());
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}