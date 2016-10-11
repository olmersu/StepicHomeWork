package com.BasicJava.checksum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bogomolov on 03.10.2016.
 * Много времени убил на Failed test #1. Test 6. Wrong answer. Делюсь советом для тех кто оперирует byte[] в своём решении.
 * В общем, проблема связана с типами. Получаем мы на вход через stream значения в типе byte. А на выход контрольную сумму мы получаем как int.
 * И в результате, по предложенному алгоритму, нам приходится делать int xor byte - это чревато упомянутым ниже расширением byte до int и, как следствие,
 * некорректной контрольной суммой в итоге. Решить эту проблему можно, если прийти от int xor byte к int xor unsigned byte. Самого по себе типа unsigned
 * byte в Java нет. Но есть несколько способов получить его, например используя DataInputStream.toUnsignedByte, о котором упоминалось в комментариях ниже.
 * Но я воспользовался следующим способом: unsigned byte = byte & 0xFF. ﻿Который мне, в итоге, и помог.
 */
public class checkSumOfStream {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new checkSumOfStream().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    private void run() {
        byte[] data = {0x33, 0x45, 0x01};
//        byte[] data;
        try (InputStream inputStream = new ByteArrayInputStream(data)){
            System.out.println(checkSumOfStream(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int readBuf;
    int checkSum = 0;
        try {
        if ((readBuf = inputStream.read()) < 0){
            return 0;
        }
        while (readBuf > 0) {
            checkSum = Integer.rotateLeft(checkSum, 1) ^ (byte) readBuf & 0xFF;
            readBuf = inputStream.read();
        }
    }
        catch (IOException e){
        throw e;
    }
        return checkSum;
}
}
