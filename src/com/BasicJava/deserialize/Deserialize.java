package com.BasicJava.deserialize;

import com.Test.Timer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Created by bogomolov on 05.10.2016.
 */
public class Deserialize {
    public static void main(String[] args) {
        Timer timer = new Timer();
        System.out.println(timer.measureTime(() -> {
            try {
                new Deserialize().run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }) + " ms");
    }

    private void run() throws Exception {
        Object[] originalAnimal = {new Animal("Fox"), new Animal("Mouse"), new Animal("Cat"), new Animal("Dog"), new Animal("Elephant")};
        Path path = Paths.get("object.bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeInt(originalAnimal.length);
            for (Object a : originalAnimal) {
                oos.writeObject(a);
            }
        }
        Animal[] in;
        try (InputStream bis = Files.newInputStream(path)) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int i;
            while ((i=bis.read())>=0)
                os.write(i);
            in = deserializeAnimalArray(os.toByteArray());
        }
        for (Animal a : in) {
            System.out.println(a.getName());
        }
    }

    public static Animal[] deserializeAnimalArray(byte[] data) throws IOException, ClassNotFoundException {
        LinkedList<Animal> deserializedAnimal = new LinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int size = ois.readInt();
            for (int j = 0; j < size; j++) {
                deserializedAnimal.add((Animal) ois.readObject());
            }
        } catch (ClassCastException | ClassNotFoundException | IOException e) {
            throw new IllegalArgumentException();
        }
        Animal[] out = deserializedAnimal.toArray(new Animal[0]);
        return out;
    }
}
