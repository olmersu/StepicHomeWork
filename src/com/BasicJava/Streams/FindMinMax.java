package com.BasicJava.Streams;

import com.Test.Timer;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by bogomolov on 13.10.2016.
 */
public class FindMinMax {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> new FindMinMax().run()) + " ms");
    }

    private void run() {
        //.of(234,45,6,3,234,56,7,8,6,867,9,0,3453,35)
        findMinMax(Stream.empty(), new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return Integer.compare(o1, o2);
                    }
                },
                (x, y) -> System.out.println(x + " " + y));
    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        Object[] minmax = new Object[2];
        stream.forEach(x -> {
            if (minmax[0] == null && minmax[1] == null) {
                minmax[0] = x;
                minmax[1] = x;
            }
            if (order.compare((T)minmax[0],x)>0){
                minmax[0] = x;
            }
            if (order.compare((T)minmax[1],x)<0){
                minmax[1] = x;
            }
        });
            minMaxConsumer.accept((T)minmax[0],(T)minmax[1]);
    }

    public static <T> void findMinMax2(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        Object[] t = stream.sorted(order).toArray();
        if (t.length > 0) {
            minMaxConsumer.accept((T) t[0], (T) t[t.length - 1]);
        } else {
            minMaxConsumer.accept(null, null);
        }
    }
}
