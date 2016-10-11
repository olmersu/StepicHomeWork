package com.BasicJava.Collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogomolov on 11.10.2016.
 */
public class SymmetricDifference {
    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(new Integer[] {1, 2, 3}));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(new Integer[] {0, 1, 2}));
        symmetricDifference(set1,set2).forEach(System.out::println);
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> copySet1 = new HashSet<T>(set1);
        Set<T> copySet2 = new HashSet<T>(set2);
        copySet1.removeAll(set2);
        copySet2.removeAll(set1);
        copySet1.addAll(copySet2);
        return Collections.unmodifiableSet(copySet1);
    }
}
