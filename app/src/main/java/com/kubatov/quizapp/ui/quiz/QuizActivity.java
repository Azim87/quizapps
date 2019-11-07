package com.kubatov.quizapp.ui.quiz;

import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreActivity;

public class QuizActivity extends CoreActivity {
    private QuizViewModel mQuizViewModel;

    @Override
    protected int getActivityLayout() {
        mQuizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        return R.layout.activity_quiz;
    }
}
