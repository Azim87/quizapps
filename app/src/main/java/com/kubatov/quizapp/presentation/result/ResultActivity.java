package com.kubatov.quizapp.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.core.CoreActivity;
import com.kubatov.quizapp.data.QuizRepository.local.model.QuizResult;

import butterknife.BindView;

public class ResultActivity extends CoreActivity {
    private static String ID = "id";
    private ResultViewModel mResultViewModel;

    @BindView(R.id.result_category)
    TextView resultCategory;
    @BindView(R.id.result_ans)
    TextView resultCorrectAnswers;
    @BindView(R.id.result_all)
    TextView resultDifficulty;
    @BindView(R.id.result_per)
    TextView resultPercent;

    public static void start(Context context, Integer id) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(ResultActivity.ID, id);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayout() {
        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        initResultViewModel();
        return R.layout.activity_result;
    }


    private void initResultViewModel() {
        mResultViewModel.getQuizResults(getIntent().getIntExtra(ID, 0));
        mResultViewModel.quizResultMutableLiveData.observe(this, quizResult -> {

        });
    }
}
