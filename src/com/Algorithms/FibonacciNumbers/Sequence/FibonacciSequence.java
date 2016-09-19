package com.Algorithms.FibonacciNumbers.Sequence;

/**
 * Created by bogomolov on 30.08.2016.
 */
import java.util.Scanner;
public class FibonacciSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num=in.nextInt();
        int fibSeq[] = new int[num+1];
        fibSeq[0]=0;
        for (int i=1; i<=num; i++){
            if (i==1){
                fibSeq[i]=1;
                continue;
            }
            fibSeq[i]=fibSeq[i-1]+fibSeq[i-2];
        }
        System.out.println(fibSeq[num]);
    }
}