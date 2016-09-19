package com.BasicJava.RectangleMethod;

/**
 * Created by bogomolov on 17.08.2016.
 */
import java.util.function.DoubleUnaryOperator;
public class RectangleMethod {
    public static void main(String[] args) {
        System.out.println(integrate(Math::sin,0,10));
//        System.out.println(integrate((x)->{return Math.sin(x);},0,10));
        System.out.println(integrate(x->1,0,10));

    }
    private static double integrate(DoubleUnaryOperator f, double a, double b) {
        double step=1;
        double i1;
        double i2;
        double precision=0.00001;
        double integral=0;
        double integral_prev;
        do {
            step=step/10;
            i1=a;
            i2=i1+(b-a)*step<b?i1+(b-a)*step:b;
            integral_prev = integral;
            integral = 0;
            System.out.println(step);
            while (i1 < b) {
                integral = integral + (i2 - i1) * f.applyAsDouble(i1);
                i1 = i2;
                i2 = i1 + (b - a) * step < b ? i1 + (b - a) * step : b;
            }
        } while (Math.abs(integral-integral_prev)>precision);
        return integral;
    }
}
