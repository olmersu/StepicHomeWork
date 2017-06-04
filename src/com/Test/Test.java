package com.Test;


import java.lang.reflect.Method;

/**
 * Created by olmer on 19.09.16.
 */

@CreatedBy (author = "Olmer", date = "01.12.2016")
public class Test {
    @CreatedBy (author = "Olmer", date = "10.12.2016")
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> new Test().run())+" ms");
    }
    @CreatedBy (author = "Olmer", date = "12.12.2016")
    private void run() {
        Class myClass = Test.class;
        Class[] paramTypes1 = new Class[] {String[].class};
        Class[] paramTypes2 = new Class[] {};
        try {
            Method  method1 =myClass.getMethod("main",paramTypes1);
            Method  method2 =myClass.getMethod("run",null);
            CreatedBy annoC = (CreatedBy) myClass.getAnnotation(CreatedBy.class);
            CreatedBy annoM = (CreatedBy) method1.getAnnotation(CreatedBy.class);
            CreatedBy annoR = (CreatedBy) method2.getAnnotation(CreatedBy.class);
            if (myClass.isAnnotationPresent(CreatedBy.class)){
                System.out.println("!!!");
            }
            System.out.println("Class Author="+annoC.author()+"; Date="+annoC.date());
            System.out.println("Mehod1 Author="+annoM.author()+"; Date="+annoM.date());
            System.out.println("Mehod2 Author="+annoR.author()+"; Date="+annoR.date());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    @CreatedBy (author = "Olmer", date = "12.12.2016")
    public void a(Integer a){
        int b=1;
    }
}