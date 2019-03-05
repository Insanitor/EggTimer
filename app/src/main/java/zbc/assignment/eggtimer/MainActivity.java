package zbc.assignment.eggtimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements EggTimerListener {

    TextView tv;
    EggTimer eggTimer;
    EggType currentEgg;
    Button startButton;
    ImageButton softButton, smileButton, hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.TimerText);
        startButton = findViewById(R.id.StartButton);
        softButton = findViewById(R.id.SoftBoilButton);
        smileButton = findViewById(R.id.SmilingButton);
        hardButton = findViewById(R.id.HardButton);
    }

    public void OnEggSelectClicked(View view) {

        switch (view.getId()) {
            case R.id.SoftBoilButton: {

                currentEgg = EggType.Soft;
                startButton.setEnabled(true);
                tv.setText("5:00");
                break;
            }
            case R.id.SmilingButton: {

                currentEgg = EggType.Smiling;
                startButton.setEnabled(true);
                tv.setText("7:00");
                break;
            }
            case R.id.HardButton: {

                currentEgg = EggType.Hard;
                startButton.setEnabled(true);
                tv.setText("10:00");
                break;
            }
            default:
                break;
        }
    }

    public void onStartClick(final View view) {
        if (currentEgg != null) {
            switch (currentEgg) {
                case Soft:
                    eggTimer = new EggTimer(5);
                    break;
                case Smiling:
                    eggTimer = new EggTimer(7 * 60);
                    break;
                case Hard:
                    eggTimer = new EggTimer(10 * 60);
                    break;
            }
            eggTimer.addListener(this);
            eggTimer.start();
            softButton.setEnabled(false);
            smileButton.setEnabled(false);
            hardButton.setEnabled(false);
            startButton.setText("Stop");
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStop(v);
                }
            });
        }
    }

    public void onStop(final View view) {
        eggTimer.removeListener(this);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClick(v);
            }
        });
        startButton.setText("Start");
        softButton.setEnabled(true);
        smileButton.setEnabled(true);
        hardButton.setEnabled(true);
        tv.setText("0:00");

    }

    @Override
    public void onCountDown(int timeLeft) {
        final int i = timeLeft;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(i / 60 + ":" + i % 60);
            }
        });
    }

    @Override
    public void OnEggTimerStopped() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Start");
                tv.setText("0:00");
                startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStartClick(v);
                    }
                });
                softButton.setEnabled(true);
                smileButton.setEnabled(true);
                hardButton.setEnabled(true);
                currentEgg = null;
            }
        });

        eggTimer.removeListener(this);
    }

    enum EggType {Soft, Smiling, Hard}

}
