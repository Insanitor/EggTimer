package zbc.assignment.eggtimer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EggTimer eggTimer;

    private TextView tv;
    private Button startButton, softButton, smileButton, hardButton;

    enum EggType {Soft, Smiling, Hard}

    EggType currentEgg;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.TimerText);
        startButton = findViewById(R.id.StartButton);
        softButton = findViewById(R.id.SoftBoilButton);
        smileButton = findViewById(R.id.SmilingButton);
        hardButton = findViewById(R.id.HardButton);
        eggTimer = new EggTimer(tv);
    }

    public void OnEggSelectClicked(View view) {
        switch (view.getId()) {
            case R.id.SoftBoilButton: {

                currentEgg = EggType.Soft;
                startButton.setEnabled(true);
                eggTimer.setTimer(5 * 60);
                tv.setText("5:00");
                break;
            }
            case R.id.SmilingButton: {

                currentEgg = EggType.Smiling;
                startButton.setEnabled(true);
                eggTimer.setTimer(7 * 60);
                tv.setText("7:00");
                break;
            }
            case R.id.HardButton: {

                currentEgg = EggType.Hard;
                startButton.setEnabled(true);
                eggTimer.setTimer(10 * 60);
                tv.setText("10:00");
                break;
            }
            default:
                break;
        }
    }

//    public void OnEggSelectClicked(View view) {
//        eggTimer.OnEggSelectClicked(view);
//    }

    public void onStartClick(final View view) {
        if (eggTimer.getTimer() > 0) {
            handler.postDelayed(eggTimer, 1000);
            startButton.setText("Stop");
            softButton.setEnabled(false);
            smileButton.setEnabled(false);
            hardButton.setEnabled(false);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onStop(view);
                }
            });
        }
    }

    public void onStop(final View view) {
        eggTimer.setTimer(0);
        startButton.setText("Start");
        softButton.setEnabled(true);
        smileButton.setEnabled(true);
        hardButton.setEnabled(true);
        tv.setText("0:00");
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClick(view);
            }
        });

    }

}
