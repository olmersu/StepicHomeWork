package com.BasicJava.AsciiCharSequence;

/**
 * Created by bogomolov on 19.09.2016.
 *
 * Напишите класс AsciiCharSequence, реализующий компактное хранение последовательности ASCII-символов (их коды влезают в один байт) в массиве байт.
 * По сравнению с классом String, хранящим каждый символ как char, AsciiCharSequence будет занимать в два раза меньше памяти.
 * Класс AsciiCharSequence должен:
 * - реализовывать интерфейс java.lang.CharSequence;
 * - иметь конструктор, принимающий массив байт;
 * - определять методы length(), charAt(), subSequence() и toString()
 * Сигнатуры методов и ожидания по их поведению смотрите в описании интерфейса java.lang.CharSequence (JavaDoc или исходники).
 * В данном задании методам charAt() и subSequence() всегда будут подаваться корректные входные параметры, поэтому их проверкой и обработкой ошибок заниматься не нужно.
 * Тем более мы еще не проходили исключения.
 * P.S. В Java 9 ожидается подобная оптимизация в самом классе String: http://openjdk.java.net/jeps/254
 */
public class AsciiCharSequence implements CharSequence{
    byte [] string;
    public AsciiCharSequence(byte [] sarr) {
        this.string = sarr;
    }

    @Override
    public int length() {
        return string.length;
    }

    @Override
    public char charAt(int index) {
        return (char)string[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        byte [] string = new byte [end-start];
        System.arraycopy(this.string, start, string, 0, end-start);
        AsciiCharSequence str = new AsciiCharSequence(string);
        return str;
    }

    @Override
    public String toString() {
        String str = new String(this.string);
        return String.valueOf(str);
    }
}
