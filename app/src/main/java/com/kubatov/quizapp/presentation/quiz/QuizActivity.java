package com.kubatov.quizapp.presentation.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.kubatov.quizapp.R;
import static com.kubatov.quizapp.presentation.main.MainFragment.DIFF_CATEGORY;
import static com.kubatov.quizapp.presentation.main.MainFragment.DIFF_DIFFICULT;
import static com.kubatov.quizapp.presentation.main.MainFragment.SEEK_BAR;

public class QuizActivity extends AppCompatActivity {
    private QuizViewModel mQuizViewModel;

    public static void start(Context context, int seekBarValue, String categoryValue, String difficultValue) {
        Intent fakeIntent = new Intent(context, QuizActivity.class);
        fakeIntent.putExtra(SEEK_BAR, seekBarValue);
        fakeIntent.putExtra(DIFF_CATEGORY, categoryValue);
        fakeIntent.putExtra(DIFF_DIFFICULT, difficultValue);
        context.startActivity(fakeIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        showFakeData();
    }

    private void showFakeData() {
        Intent fakeIntent = getIntent();
        int seekBar = fakeIntent.getIntExtra(SEEK_BAR, 0);
        String category = fakeIntent.getStringExtra(DIFF_CATEGORY);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);
        Toast.makeText(this, "onActivityResult: " + seekBar + "\n" + " " + category + " " + difficulty, Toast.LENGTH_SHORT).show();
        TextView view = findViewById(R.id.text_quizs);
        view.setText(" " + seekBar + " " +  category + " " + difficulty );
    }
}
