package com.BasicJava.Exceptions.ParrentClass;

/**
 * Created by bogomolov on 26.09.2016.
 */
public class ParentClass {
        public static void main(String[] args) {
            System.out.println(getCallerClassAndMethodName());
            anotherMethod();
        }

        private static void anotherMethod() {
            System.out.println(getCallerClassAndMethodName());
        }

        public static String getCallerClassAndMethodName() {
            StackTraceElement [] stack = new Throwable().getStackTrace();
            if (stack.length < 3) return null;
//            else return stack[stack.length-1].getClassName()+"#"+stack[stack.length-1].getMethodName();
            else return stack[2].getClassName()+"#"+stack[2].getMethodName();
        }
}
