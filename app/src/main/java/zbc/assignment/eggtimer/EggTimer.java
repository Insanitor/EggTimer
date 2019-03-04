package zbc.assignment.eggtimer;

import java.util.ArrayList;
import java.util.List;

public class EggTimer extends Thread {


    List<EggTimerListener> listeners = new ArrayList<>();
    private int timer;

    public EggTimer() {
    }

    public EggTimer(int time) {
        timer = time;
    }

    @Override
    public void run() {
        if (timer > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer--;
            for (EggTimerListener l : listeners) {
                l.onCountDown(timer);
            }
            run();
        } else {
            for (EggTimerListener l : listeners) {
                l.OnEggTimerStopped();
            }
        }
    }

    int getTimer() {
        return timer;
    }

    void setTimer(int i) {
        timer = i;
    }

    public void addListener(EggTimerListener listener) {
        listeners.add(listener);
    }

    public void removeListener(EggTimerListener listener) {

        listeners.remove(listener);
    }

    private String getTimeFormat(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
