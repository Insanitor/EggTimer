package zbc.assignment.eggtimer;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EggTimer extends Thread {

    List<EggTimerPresenter.View> listeners = new ArrayList<>();
    private int timer;

    TimerState runningState, pendingState, currentState;

    public EggTimer() {
    }

    public EggTimer(int time) {
        timer = time;

        runningState = new RunningState() {
            @Override
            public void start() {
                if (timer > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timer--;
                    for (EggTimerPresenter.View l : listeners) {
                        l.onCountDown(timer);
                    }
                    run();
                } else {
                    for (EggTimerPresenter.View l : listeners) {
                        l.onEggTimerStopped();
                        currentState = pendingState;
                    }
                }
            }
        };

        pendingState = new PendingState() {
            @Override
            public void start() {
                currentState = runningState;
                currentState.start();
            }
        };

        currentState = pendingState;
    }

    @Override
    public void run() {
        currentState.start();
    }

    int getTimer() {
        return timer;
    }

    void setTimer(int i) {
        timer = i;
    }

    public void addListener(EggTimerPresenter.View listener) {
        listeners.add(listener);
    }

    public void removeListener(EggTimerPresenter.View listener) {

        listeners.remove(listener);
    }

    private String getTimeFormat(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
