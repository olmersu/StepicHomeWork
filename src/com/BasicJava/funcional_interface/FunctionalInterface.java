package com.BasicJava.funcional_interface;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by bogomolov on 12.10.2016.
 */
public class FunctionalInterface {
    public static void main(String[] args) {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = ternaryOperator(condition, ifTrue, ifFalse);
        System.out.println(safeStringLength.apply("abc"));
    }

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
// вместо лямба выражения было это:
//        return new Function<T, U>() {
//            @Override
//            public U apply(T t) {
//                return condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
//            }
//        };
    }
}

