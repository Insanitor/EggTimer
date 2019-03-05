package zbc.assignment.eggtimer;

public interface EggTimerListener {
    public void onCountDown(int timeLeft);

    public void onEggTimerStopped();
}
