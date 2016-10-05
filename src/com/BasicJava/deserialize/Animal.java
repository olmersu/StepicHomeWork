package com.BasicJava.deserialize;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by bogomolov on 05.10.2016.
 */
public class Animal implements Serializable{
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}
