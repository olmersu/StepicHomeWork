package com.BasicJava.RoboException;

import com.Test.Test;

import java.io.IOException;

/**
 * Created by bogomolov on 27.09.2016.
 */
public class Robot implements RobotConnection, RobotConnectionManager{
    private int x;
    private int y;
    private String name;
    int connections=0;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        new Robot(0,0,"Petr").run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
    private void run() {
        moveRobot(this,2,2);
    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            try (RobotConnection con = robotConnectionManager.getConnection()){
                con.moveRobotTo(toX, toY);
                flag = true;
                i=4;
            }
            catch (RobotConnectionException e){
                if (i==2) {
                    throw (e);
                }
            }
            catch (RuntimeException e){
                if (!flag) throw e;
            }
        }
    }

    @Override
    public RobotConnection getConnection() {
        if (this.connections==2) {
            System.out.println("Соединение установлено с роботом "+this.name+", attempt "+this.connections);
            return this;
        }
        this.connections++;
        throw new RobotConnectionException("can't connect, attempt "+this.connections);
    }

    @Override
    public void moveRobotTo(int x, int y) {
//        this.x=x;
//        this.y=y;
//        System.out.println("Робот "+this.name+" переместился в координату "+x+", "+y);
        throw new RuntimeException("ой222");
    }

    @Override
    public void close() {
        System.out.println("попытка разорвать соединение");
        throw new RuntimeException("Соединение разорвано");
//        System.out.println("соединение с роботом "+this.name+" закрыто");
    }


    public class RobotConnectionException extends RuntimeException {

        public RobotConnectionException(String message) {
            super(message);

        }
        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public Robot(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }
}
