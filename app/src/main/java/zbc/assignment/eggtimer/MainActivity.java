package zbc.assignment.eggtimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements EggTimerPresenter.View {

    TextView tv;
    EggType currentEgg;
    Button startButton;
    ImageButton softButton, smileButton, hardButton;
    EggTimerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.TimerText);
        startButton = findViewById(R.id.StartButton);
        softButton = findViewById(R.id.SoftBoilButton);
        smileButton = findViewById(R.id.SmilingButton);
        hardButton = findViewById(R.id.HardButton);
        presenter = new EggTimerPresenter(this);
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
                    presenter.start(5 * 60 );
                    break;
                case Smiling:
                    presenter.start(7 * 60);
                    break;
                case Hard:
                    presenter.start(10 * 60);
                    break;
            }
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
        currentEgg = null;
        presenter.stop();

    }

    @Override
    public void onCountDown(int time) {
        final int i = time;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(i / 60 + ":" + i % 60);
            }
        });

    }

    @Override
    public void onEggTimerStopped() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartClick(v);
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startButton.setText("Start");
                tv.setText("0:00");
                softButton.setEnabled(true);
                smileButton.setEnabled(true);
                hardButton.setEnabled(true);
                currentEgg = null;
            }
        });
    }

    enum EggType {Soft, Smiling, Hard}

}
