package com.Algorithms.FibonacciNumbers.SequenceModulo;

/**
 * Created by bogomolov on 30.08.2016.
 */
public class FibonacciSequenceModulo {
    public static void main(String[] args) {
/*
        Scanner in = new Scanner(System.in);
        long m=in.nextLong();
        long n=in.nextLong();
*/

        long m = 29711;
        int n = 100;
        m = 4547482994678400L;
        n = 272135;
// 27213 3104 11421
        int pisano1 = MathFunc.Pisano(n);
//        System.out.println(MathFunc.Pisano(300));
//        System.out.println(MathFunc.FibonacciSequence(100000));
        /*
        Vector sequence = new Vector();
        sequence = MathFunc.factorization_array(m, sequence);
        sequence = MathFunc.normalisation(sequence);
        long pisano1 = 1;
        long pisano2 = 1;
        for (int i = 0; i < sequence.size(); i++) {
            if (((PrimeElement) sequence.get(i)).getExp() > 1) {
                pisano2 = (long) Math.pow((double) ((PrimeElement) sequence.get(i)).getBase(), ((PrimeElement) sequence.get(i)).getExp() - 1)
                        * MathFunc.Pisano(((PrimeElement) sequence.get(i)).getBase()); // Pi(p^k)=p^(k-1)*Pi(p)
                // https://ru.wikipedia.org/wiki/%D0%9F%D0%B5%D1%80%D0%B8%D0%BE%D0%B4_%D0%9F%D0%B8%D0%B7%D0%B0%D0%BD%D0%BE
                pisano1 = MathFunc.lcdCalc(pisano1, pisano2);
            }
            if (((PrimeElement) sequence.get(i)).getExp() == 1) {
                pisano2 = MathFunc.Pisano(((PrimeElement) sequence.get(i)).getBase()); // Pi(p)
                pisano1 = MathFunc.lcdCalc(pisano1, pisano2);
            }
        }
*/
        System.out.println(n + " " + pisano1 + " "+ MathFunc.fibonacciSequenceMod(m%pisano1,n));
    }
}

class PrimeElement implements Comparable<PrimeElement>{
    long base;
    long exp;
    public PrimeElement(long base, long exp){
        this.base=base;
        this.exp=exp;
    }
    public PrimeElement(long base){
        this.base=base;
        this.exp=1;
    }
    public long getBase(){
        return this.base;
    }
    public long getExp(){
        return this.exp;
    }
    public void setBase(long base){
        this.base=base;
    }
    public void setExp(long exp){
        this.exp=exp;
    }

    @Override
    public int compareTo(PrimeElement other) {
        return Long.compare(this.getBase(), other.getBase());
    }
}