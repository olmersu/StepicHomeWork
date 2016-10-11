package com.BasicJava.Collections;
/**
 * Created by bogomolov on 11.10.2016.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EvenList {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new LinkedList<>();
        String str = in.readLine();
        if (str!=null) {
            for (String s : str.split(" ")) {
                list.add(Integer.parseInt(s));
            }
            Iterator<Integer> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (i % 2 == 0) {
                    it.next();
                    it.remove();
                } else {
                    it.next();
                }
                i++;
            }
            Collections.reverse(list);
            list.forEach((k) -> System.out.printf("%d ", k));
        }
    }
}
