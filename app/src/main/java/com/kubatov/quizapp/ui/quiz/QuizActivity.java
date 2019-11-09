package com.kubatov.quizapp.ui.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreActivity;
import com.kubatov.quizapp.ui.SplashActivity;
import com.kubatov.quizapp.ui.main.MainActivity;

import butterknife.BindView;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel mQuizViewModel;

    public static void start(Context context) {
        context.startActivity(new Intent(context, QuizActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
    }
}
