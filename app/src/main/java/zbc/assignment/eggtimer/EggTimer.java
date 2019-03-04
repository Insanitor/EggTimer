package zbc.assignment.eggtimer;

import android.os.Handler;
import android.widget.TextView;

public class EggTimer implements Runnable {

    private int timer;
    private TextView textBox;

    private final Handler handler = new Handler();

    @Override
    public void run() {
        if(timer > 0)
        {
            timer--;
            textBox.setText(getTimeFormat(timer));
            handler.postDelayed(this,1000);
        }
    }

    EggTimer(TextView text) {
        textBox = text;
    }

    void setTimer(int i)
    {
        timer = i;
    }

    int getTimer()
    {
        return timer;
    }

    private String getTimeFormat(int timeInSeconds)
    {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
