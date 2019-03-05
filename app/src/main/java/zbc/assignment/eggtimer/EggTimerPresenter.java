package zbc.assignment.eggtimer;

import android.view.View;

public class EggTimerPresenter implements EggTimerListener,TimerState {

    View view;

    TimerState currentState;

    public EggTimerPresenter(View v) {
        view = v;
    }

    EggTimer eggTimer;

    public void stop() {
        eggTimer.setTimer(0);
        eggTimer.removeListener(view);
    }

    public void start(int timeToCook) {
        eggTimer = new EggTimer(timeToCook);
        eggTimer.addListener(view);
        eggTimer.start();
    }

    @Override
    public void onCountDown(int timeLeft) {
        view.onCountDown(timeLeft);
    }

    @Override
    public void onEggTimerStopped() {
        view.onEggTimerStopped();
    }

    @Override
    public void start() {
    }

    public interface View {
        void onCountDown(int time);

        void onEggTimerStopped();
    }
}

