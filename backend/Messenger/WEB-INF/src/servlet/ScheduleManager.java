package servlet;

import java.util.Timer;

public class ScheduleManager extends Thread {

    private static ScheduleManager instance = new ScheduleManager();
    private static Timer timer = new Timer();

    private ScheduleManager() {
    }

    @Override
    public synchronized void run() {
        if (timer != null) {
            // 既にtimerがあれば停止する
            timer.cancel();
            System.out.println("定期実行の停止");
        }
        // timerの開始
        System.out.println("定期実行の開始");
        timer = new Timer();
        timer.schedule(Schedule.getInstance(), 0, 1 * 1000);
    }

    public static ScheduleManager getInstance() {
        return instance;
    }
}