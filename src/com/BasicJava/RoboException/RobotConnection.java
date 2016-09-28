package com.BasicJava.RoboException;

/**
 * Created by bogomolov on 27.09.2016.
 */
public interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}
