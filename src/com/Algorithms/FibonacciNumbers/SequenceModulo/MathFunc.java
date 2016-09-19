package com.Algorithms.FibonacciNumbers.SequenceModulo;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

/**
 * Created by bogomolov on 31.08.2016.
 */
/*
Вычисление наименьшего общего кратного (НОК)
lcm(m,n) (от англ. Least Common Multiple)
 */

public class MathFunc {

    /**
     *Вычисление наибольшего общего делителя (НОД)
     *gcd(m,n) (от англ. greatest common divisor);
     *hcf(m,n) (от брит. highest common factor).
     *Использован алгоритм Евклида
     * @param m
     * @param n
     * @return наибольиший общий делитель чисел m и n
     */
    public static long gcdCalc(long m, long n) {
        if (n==0) return m;
        if (m==0) return n;
        if (m>=n) return gcdCalc(m%n,n);
        if (m<=n) return gcdCalc(m,n%m);
        return 1;
    }
    public static long lcdCalc(long m, long n){
        Vector seqM = new Vector();
        Vector seqN = new Vector();
        seqM = factorization_array(m,seqM);
        seqN = factorization_array(n,seqN);
        seqM = normalisation(seqM);
        seqN = normalisation(seqN);
        long r = 1;
        long exp=0;
        for (int i=0; i<(seqM.size()>seqN.size()?seqM.size():seqN.size()); i++) {
            exp=Math.max(i<seqM.size()?(((PrimeElement) seqM.get(i)).getExp()):0,i<seqN.size()?(((PrimeElement) seqN.get(i)).getExp()):0);
            if (seqM.size()>seqN.size())
                r=r*(long)Math.pow((double)((PrimeElement)seqM.get(i)).getBase(), (double)exp);
            else
                r=r*(long)Math.pow((double)((PrimeElement)seqN.get(i)).getBase(), (double)exp);
        }
        return r;
    }
    public static boolean isPrime(long a){
        long i1, i2, i3, i4, i5, i6, i7, i8, bound;
        if (a == 0 || a == 1)
            return false;
        if (a == 2 || a == 3 || a == 5 || a == 7 || a == 11 || a == 13 || a == 17 || a == 19 || a == 23 || a == 29)
            return true;
        if (a%2 == 0 || a%3 == 0 || a%5 == 0 || a%7 == 0 || a%11 == 0 || a%13 == 0 || a%17 == 0 || a%19 == 0 || a%23 == 0 || a%29 == 0)
            return false;
        bound = (long) Math.sqrt(a);
        i1 = 31; i2 = 37; i3 = 41; i4 = 43; i5 = 47; i6 = 49; i7 = 53; i8 = 59;
        while (i8 <= bound && a%i1!=0 && a%i2!=0 && a%i3!=0 && a%i4!=0 && a%i5!=0 && a%i6!=0 && a%i7!=0 && a%i8!=0)
        {
            i1 += 30; i2 += 30; i3 += 30; i4 += 30; i5 += 30; i6 += 30; i7 += 30; i8 += 30;
        }
        if (i8 <= bound ||
                i1 <= bound && a % i1 == 0 ||
                i2 <= bound && a % i2 == 0 ||
                i3 <= bound && a % i3 == 0 ||
                i4 <= bound && a % i4 == 0 ||
                i5 <= bound && a % i5 == 0 ||
                i6 <= bound && a % i6 == 0 ||
                i7 <= bound && a % i7 == 0)
            return false;
        return true;
    }

    /**
     * Метод для нахождения делителя числа
     * Источник https://ru.wikipedia.org/wiki/%D0%A0%D0%BE-%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%9F%D0%BE%D0%BB%D0%BB%D0%B0%D1%80%D0%B4%D0%B0
     * @param n
     * @return делитель числа n
     */
    public static long pollard(long n){
        long x, y;
        do {
            Random rand = new Random();
            n = Math.abs(n);
            y = rand.nextInt(Math.abs(n <= 2 ? (int) n : (int) n - 2)) | 1;
            while (!isPrime(y)) y += 2;
            x = 1;
            int i = 1;
            int stage = 2;
            while (gcdCalc(n, Math.abs(y - x)) == 1) {
                if (i == stage) {
                    x = y;
                    stage = stage * 2;
                }
                y = (y * y + 1) % n;
                i++;
            }
        }while (gcdCalc(n,Math.abs(y-x))==n || isPrime(n)); // без этого условия, при n=9 высока вероятность того что функция вернет 9,
        // т.е. разлоежение числа 9 будет 9*1, что не правильно, возврящатсять должны делители меньше n, если конечно n не простое.
        return gcdCalc(n,Math.abs(y-x));
    }
    public static void factorization(long a){
        long b=1;
        if (a!=1 && a!=0) {
            while (!isPrime(a)) {
                b = pollard(a);
                a = a / b;
                if (!isPrime(b)) factorization(b);
                if (isPrime(a)) {
                    System.out.println(a + " - prime");
                }
                if (isPrime(b)) {
                    System.out.println(b + " - prime");
                }
            }
        }
    }
    public static Vector factorization_array(long a, Vector sequence){
        PrimeElement tempo = new PrimeElement(1,1);
        int i = 1;
        long b=1;
        if (a==1 || a==0 || isPrime(a)) {
            sequence.add(new PrimeElement(a));
            return sequence;
        }
        while (!isPrime(a)){
            b=pollard(a);
            a=a/b;
            if (!isPrime(b)) factorization_array(b,sequence);
            if (isPrime(a)) {
                i = 0;
                tempo = new PrimeElement(0,0);
                for (i=0;i<sequence.size();i++){
                    tempo=(PrimeElement) sequence.get(i);
                    if (tempo.getBase()==a) break;
                }
                if (i==sequence.size()) sequence.add(new PrimeElement(a));
                else sequence.set(i,new PrimeElement(a,tempo.getExp()+1));
            }
            if (isPrime(b)) {
                i = 0;
                tempo = new PrimeElement(0,0);
                for (i=0;i<sequence.size();i++){
                    tempo=(PrimeElement) sequence.get(i);
                    if (tempo.getBase()==b) break;
                }
                if (i==sequence.size()) sequence.add(new PrimeElement(b));
                else sequence.set(i,new PrimeElement(b,tempo.getExp()+1));
            }
        }
        return sequence;
    }
    public static Vector normalisation (Vector sequence){
        Collections.sort(sequence);
        int j=2;
        Vector fullSequence = new Vector();
        for (int i=0;i<sequence.size();i++) {
            while (j <= ((PrimeElement) sequence.get(i)).getBase()) {
                if (((PrimeElement) sequence.get(i)).getBase() != j)
                    fullSequence.add(new PrimeElement(j, 0));
                else
                    fullSequence.add(new PrimeElement(((PrimeElement) sequence.get(i)).getBase(), ((PrimeElement) sequence.get(i)).getExp()));
                do j++; while(!isPrime(j));
            }
        }
        return fullSequence;
    }
    public static BigInteger FibonacciSequence(int num){
        BigInteger i1 = new BigInteger("0");
        BigInteger i2 = new BigInteger("1");
        BigInteger i3 = new BigInteger("1");
        for (int i=2; i<=num; i++){
            i3=i2.add(i1);
            i1=i2.abs();
            i2=i3.abs();
        }
        return i3;
    }
    public static int fibonacciSequenceMod(long num, int m){
        if (num==0) return 0;
        if (num==1) return 1;
        int i1 = 0;
        int i2 = 1;
        int i3 = 1;
        for (int i=2; i<=num; i++){
            i3=(i2+i1)%m;
            i1=i2;
            i2=i3;
        }
        return i3;
    }
    public static int Pisano (int m){
        if (m==1) return 1;
        if (m==2) return 3;
        long i1 = 0;
        long i2 = 1;
        long i3 = 1;
        int k=0;
        int j=2;
        do {
            i1 = i2;
            i2 = i3;
            i3 = fibonacciSequenceMod(j+2,m);
            if (j==3100){
                k=1;
            }
            if (i1==0 && i2==1 && i3==1) {
                return j;
            }
            j++;
        } while (i1!=0 || i2!=1 || i3!=1);
        return 0;
    }
}
