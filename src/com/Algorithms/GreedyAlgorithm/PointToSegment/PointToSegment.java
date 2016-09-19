package com.Algorithms.GreedyAlgorithm.PointToSegment;

/**
 * Created by bogomolov on 08.09.2016.
 * По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
 * В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n строк содержит по два числа 0≤l≤r≤10^9, задающих начало и конец отрезка.
 * Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.
 *Sample Input 1:
 *3
 *1 3
 *2 5
 *3 6
 *Sample Output 1:
 *1
 *3
 *Sample Input 2:
 *4
 *4 7
 *1 3
 *2 5
 *5 6
 *Sample Output 2:
 *2
 *3 6
 */
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class PointToSegment {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amountSegments = in.nextInt();
//        int amountSegments = 4;
        Vector segments = new Vector();
        Vector points = new Vector();
        for (int i=0;i<amountSegments;i++){
            segments.add(new Segment(in.nextInt(),in.nextInt()));
        }
/*
        segments.add(new Segment(4,7));
        segments.add(new Segment(1,3));
        segments.add(new Segment(2,5));
        segments.add(new Segment(5,6));
*/
        Collections.sort(segments);
        int i=0;
        while (i<amountSegments){
            points.add(new Segment(((Segment) segments.get(i)).getx2(),
                    ((Segment) segments.get(i)).getx2()));
            i++;
            while (i < amountSegments && ((Segment)segments.get(i)).getx1()<=((Segment)points.lastElement()).getx1()){
                i++;
            }
        }
        System.out.println(points.size());
        for (i=0;i<points.size();i++){
            ((Segment)points.get(i)).printPoint();
        }
    }
}

class Segment implements Comparable<Segment>{
    int x1;
    int x2;
    public Segment(int x1, int x2){
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
    public int compareTo(Segment other) {
        return Integer.compare(this.getx2(), other.getx2());
    }
    public void printSegment(){
        System.out.println(this.getx1()+" "+this.getx2());
    }
    public void printPoint(){
        System.out.print(this.getx1()+" ");
    }
}
