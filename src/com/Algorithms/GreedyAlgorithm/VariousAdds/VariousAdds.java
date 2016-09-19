package com.Algorithms.GreedyAlgorithm.VariousAdds;

/**
 * Created by bogomolov on 08.09.2016.
 * Задача на программирование: различные слагаемые
 * По данному числу 1≤n≤10^9
 * найдите максимальное число k, для которого n можно представить как сумму k различных натуральных слагаемых. Выведите в первой строке число k, во второй — k
 * слагаемых.
 * Sample Input 1:
 * 4
 * Sample Output 1:
 * 2
 * 1 3
 * Sample Input 2:
 * 6
 * Sample Output 2:
 * 3
 * 1 2 3
 */
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class VariousAdds {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int amount = in.nextInt();
//        int BPVolume = in.nextInt();
        int num = 100;
        Vector things = new Vector();
//        for (int i = 0; i < amount; i++) {
//            things.add(new Thing(in.nextInt(), in.nextInt()));
//        }
        int i = 1;
        int sum = 0;
        while (sum<num){
            if (num-sum-i>=i+1){
                sum+=i;
                System.out.println(i);
            }
            else {
                System.out.println(num-sum);
                sum=num;
            }
            i++;
        }
    }
}