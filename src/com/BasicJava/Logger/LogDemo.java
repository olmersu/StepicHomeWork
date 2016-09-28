package com.BasicJava.Logger;
import com.BasicJava.ClassC;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * Created by bogomolov on 28.09.2016.
 */
//Пошаговая стратегия (для тех, кто очень хочет уложиться в мягкий дедлайн)
//        - инициализируем переменную для логгера класса А и  устанавливаем ему заданный уровень
//        -  то же для класса B
//
//        - инициализируем переменную для логгера класса "org.stepic.java"
//        - лезем в документацию и смотрим что там с родителями логгера - как сделать так, чтобы сообщения не передавались выше
//        - устанавливаем ему эту фичу
//
//        - инициализируем ConsoleHandler
//        - устанавливаем ему нужный уровень
//        - добавляем его к логгеру нашего самого верхнего уровня
//
//        - инициализируем нужный форматтер
//        - устанавливаем его нашему ConsoleHandler

public class LogDemo {
    public static void main(String[] args) {
        System.out.println(ClassA.class.getName());
        System.out.println(ClassB.class.getName());
        System.out.println(com.BasicJava.ClassC.class.getName());

    }
    private static void configureLogging() {
        Logger LOGGERA = Logger.getLogger(ClassA.class.getName());
        LOGGERA.setLevel(Level.ALL);
        Logger LOGGERB = Logger.getLogger(ClassB.class.getName());
        LOGGERB.setLevel(Level.ALL);
        Logger LOGGERC = Logger.getLogger(com.BasicJava.ClassC.class.getName());
        LOGGERC.setLevel(Level.ALL);
        LOGGERC.setUseParentHandlers(false);
        ConsoleHandler consHandler = new ConsoleHandler();
        consHandler.setLevel(Level.ALL);
        LOGGERC.addHandler(consHandler);
        consHandler.setFormatter(new XMLFormatter());
    }

    /**
     * Created by bogomolov on 28.09.2016.
     */
    public static class ClassA {
    }

    /**
     * Created by bogomolov on 28.09.2016.
     */
    public static class ClassB {
    }
}
