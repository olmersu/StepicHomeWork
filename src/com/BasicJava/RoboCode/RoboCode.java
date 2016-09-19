package com.BasicJava.RoboCode;

/**
 * Created by bogomolov on 16.08.2016.
 */
public class RoboCode {
    public static void main(String[] args) {
        Robot robot = new Robot();
        robot.turnRight();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        System.out.println(robot.getX());
        System.out.println(robot.getY());
        System.out.println(robot.getDirection());
        moveRobot(robot,-3,-3);
        System.out.println(robot.getX());
        System.out.println(robot.getY());
        System.out.println(robot.getDirection());
    }
    public static void moveRobot(Robot robot, int toX, int toY) {
        int roboX = robot.getX();
        int roboY = robot.getY();
        Direction roboDir = robot.getDirection();
        int k = 0;
        int i = 0;
        if (toY != roboY) {
            int alpha1Y = 0; //Direction.RIGHT
            int alpha2Y = 0;
            if (roboDir == Direction.DOWN) {
                alpha1Y = 270;
            }
            if (roboDir == Direction.UP) {
                alpha1Y = 90;
            }
            if (roboDir == Direction.LEFT) {
                alpha1Y = 180;
            }
            if (toY < roboY) {
                alpha2Y = 270;
            }
            if (toY > roboY) {
                alpha2Y = 90;
            }
            k = alpha2Y - alpha1Y;
            if (k > 0 && k != 270 ){
                for (i=0; i<k/90; i++){
                    robot.turnLeft();
                }
            }
            if (k == 270){
                robot.turnRight();
            }
            if (k < 0){
                for (i=0; i<((-1*k)/90); i++){
                    robot.turnRight();
                }
            }
            for (i=0; i<(Math.abs(toY-roboY)); i++){
                robot.stepForward();
            }
        }
        roboDir = robot.getDirection();
        if (toX != roboX) {
            int alpha1Y = 0; //Direction.RIGHT
            int alpha2Y = 0;
            if (roboDir == Direction.DOWN) {
                alpha1Y = 270;
            }
            if (roboDir == Direction.UP) {
                alpha1Y = 90;
            }
            if (roboDir == Direction.LEFT) {
                alpha1Y = 180;
            }
            if (toX < roboX) {
                alpha2Y = 180;
            }
            if (toY > roboY) {
                alpha2Y = 0;
            }
            k = alpha2Y - alpha1Y;
            if (k > 0){
                for (i=0; i<k/90; i++){
                    robot.turnLeft();
                }
            }
            if (k == -270){
                robot.turnLeft();
            }
            if (k < 0  && k != -270){
                for (i=0; i<((-1*k)/90); i++){
                    robot.turnRight();
                }
            }
            for (i=0; i<(Math.abs(toX-roboX)); i++){
                robot.stepForward();
            }
        }
        return;
    }

}

