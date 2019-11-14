package com.kubatov.quizapp.presentation.result;

import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreActivity;

public class ResultActivity extends CoreActivity {
    private ResultViewModel mResultViewModel;

    @Override
    protected int getActivityLayout() {
        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        return R.layout.activity_result;
    }
}
