package com.kubatov.quizapp.ui.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import static com.kubatov.quizapp.ui.main.MainFragment.DIFF_CATEGORY;
import static com.kubatov.quizapp.ui.main.MainFragment.DIFF_DIFFICULT;
import static com.kubatov.quizapp.ui.main.MainFragment.SEEK_BAR;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel mQuizViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        showFakeData();
    }

    private void showFakeData() {
        Intent fakeIntent = getIntent();
        int seekBar = fakeIntent.getIntExtra(SEEK_BAR, 0);
        String category = fakeIntent.getStringExtra(DIFF_CATEGORY);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);
        Log.d("ololo", "onActivityResult: " + seekBar + " " + category + " " + difficulty);
        Toast.makeText(this, "onActivityResult: " + seekBar + "\n" + " " + category + " " + difficulty, Toast.LENGTH_SHORT).show();
    }
}
