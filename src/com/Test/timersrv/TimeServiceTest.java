package com.Test.timersrv;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadFactory;

/**
 * Created by olmer on 15.12.16.
 */

public class TimeServiceTest {
    public static void main(String[] args) throws InterruptedException {
        int timeMs = 10000;
        TimeService.instance().start();
        TimeService.instance().sheduleTask(new TimerTask() {
            @Override
            public void run() {
                System.out.append("Timer run!\n");
                TimeService.instance().stop();
            }
        }, timeMs);
        System.out.println("Msg after timer starts");
        Thread.sleep(5000);
        System.out.println("Msg after local timer 5 sec starts");
    }
}

class TimeService {
    private static TimeService timeService;

    private TimeService() {
    }

    ;

    private Timer timer;

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Timer getTimer() {

        return timer;
    }

    public static TimeService instance() {
        if (timeService == null) {
            timeService = new TimeService();
        }
        return timeService;
    }

    public void start() {
        timer = new Timer();
    }

    public void sheduleTask (TimerTask timerTask, int timeMs){
        timer.schedule(timerTask,timeMs);
    }

    public void stop() {
        timer.cancel();
    }
}
