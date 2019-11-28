package com.kubatov.quizapp.presentation.result;

import android.content.Context;
import android.content.Intent;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreActivity;

public class ResultActivity extends CoreActivity {
    private static String ID = "id";
    private ResultViewModel mResultViewModel;

    public static void start(Context context, Integer id){
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(ResultActivity.ID, id);
        context.startActivity(intent);

    }

    @Override
    protected int getActivityLayout() {
        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        return R.layout.activity_result;
    }
}
