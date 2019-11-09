package com.kubatov.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private long mills = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setSplashTime();
    }

    private void setSplashTime() {
        new CountDownTimer(mills, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mills = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                getMainActivity();
            }
        }.start();
    }

    private void getMainActivity() {
        MainActivity.start(this);
        finish();
    }
}
