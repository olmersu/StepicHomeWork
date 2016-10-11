package com.BasicJava.Generics;

/**
 * Created by bogomolov on 10.10.2016.
 */
public class Pair <T1,T2> {
    private T1 t1;
    private T2 t2;
    private Pair(T1 t1, T2 t2) {
        this.t1=t1;
        this.t2=t2;
    }

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(null, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"
        System.out.println(i+": "+s);
        Pair<Integer, String> pair2 = Pair.of(null, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);
    }

    static <T1,T2> Pair<T1, T2> of(T1 t1,T2 t2) {
        return new Pair<T1, T2>(t1,t2);
    }

    T2 getSecond() {
        return this.t2;
    }

    T1 getFirst() {
        return this.t1;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag1 = false;
        if (this.t1==null)
            if(((Pair)obj).t1==null) {
                flag1 = true;
            } else {
                return false;
            }
        else flag1 = this.t1.equals(((Pair)obj).t1);
        boolean flag2 = false;
        if (this.t2==null)
            if(((Pair)obj).t2==null) {
                flag2 = true;
            } else {
                return false;
            }
        else flag2= this.t2.equals(((Pair)obj).t2);
        return flag1&&flag2;
    }

    @Override
    public int hashCode() {
        return (this.t1 != null ? t1.hashCode():0) + (this.t2!= null ? t2.hashCode():0);
    }
}
