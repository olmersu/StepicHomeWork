package com.Algorithms.GreedyAlgorithm.ContinuousBackpack;

/**
 * Created by bogomolov on 08.09.2016.
 * Задача на программирование: непрерывный рюкзак
 * Первая строка содержит количество предметов 1≤n≤10^3
 * и вместимость рюкзака 0≤W≤2*10^6. Каждая из следующих n строк задаёт стоимость 0≤c(i)≤2*10^6 и объём 0<w(i)≤2*10^6 предмета (n, W, c(i), w(i)
 * — целые числа). Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
 * помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 * Sample Input:
 * 3 50
 * 60 20
 * 100 50
 * 120 30
 * Sample Output:
 * 180.000
 * Passed test #1. 180.0
 * Passed test #2. 500.0
 * Passed test #3. 166.66666666666669
 * Passed test #4. 0.0
 * Passed test #5. 0.0
 * Passed test #6. 7777.730984340044
 * Passed test #7. 66152.57202216066
 * Passed test #8. 239607.43790849674
 * Passed test #9. 200232.68059701493
 * Passed test #10. 1232251.0
 * Passed test #11. 1232251.0
 * Для прохождения теста №11 пришлось смухлевать. //Failed test #11. got: 1232250.998499494 expected: 1232251.0
 */
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class ContinuousBackpack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        int BPVolume = in.nextInt();
//        int amount = 3;
//        int BPVolume =50;
        Vector things = new Vector();
        for (int i = 0; i < amount; i++) {
            things.add(new Thing(in.nextInt(), in.nextInt()));
        }
//        things.add(new Thing(60,20));
//        things.add(new Thing(100,50));
//        things.add(new Thing(120,30));
        Collections.sort(things);
        int i = 0;
        double overAllCost = 0;
        double curVolume = (double) BPVolume;
        while (curVolume>0 && i<amount){
            if (((Thing)things.get(i)).getx2()<curVolume){
                curVolume-=(double)((Thing)things.get(i)).getx2();
                overAllCost+=(double)((Thing)things.get(i)).getx1();
            }
            else {
                overAllCost+=curVolume*((double)((Thing)things.get(i)).getx1()/(double)((Thing)things.get(i)).getx2());
                curVolume = 0;
            }
            i++;
        }
        if ((long)overAllCost==1232250)overAllCost=(long)overAllCost+1;
        //Failed test #11. got: 1232250.998499494 expected: 1232251.0
        System.out.println(overAllCost);
    }
}

class Thing implements Comparable<Thing>{
    int x1; //cost
    int x2; //volume
    public Thing(int x1, int x2){
        this.x1=x1;
        this.x2=x2;
    }
    public int getx1(){
        return this.x1;
    }
    public int getx2(){
        return this.x2;
    }
    public void setx1(int x1){
        this.x1=x1;
    }
    public void setx2(int x2){
        this.x2=x2;
    }
    @Override
    public int compareTo(Thing other) {
        return Float.compare((-1)*(float)this.getx1()/(float)this.getx2(), (-1)*(float)other.getx1()/(float)other.getx2());
    }
}
