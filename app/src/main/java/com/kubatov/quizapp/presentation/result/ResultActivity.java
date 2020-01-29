package com.kubatov.quizapp.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kubatov.quizapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {
    private static String ID = "id";
    private ResultViewModel mResultViewModel;

    @BindView(R.id.result_category)
    TextView resultCategory;
    @BindView(R.id.result_ans)
    TextView resultCorrectAnswers;
    @BindView(R.id.result_all)
    TextView resultDifficulty;
    @BindView(R.id.result_percents)
    TextView resultPercent;
    @BindView(R.id.result_button_finish)
    Button resultFinishButton;

    public static void start(Context context, Integer id) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(ResultActivity.ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        initResultViewModel();
        finishResult();
    }

    private void initResultViewModel() {
        mResultViewModel.getQuizResults(getIntent().getIntExtra(ID, 0));
        mResultViewModel.quizResultMutableLiveData.observe(this, quizResult -> {
            resultCategory.setText(quizResult.getQuestions().get(0).getCategory());
            resultDifficulty.setText(String.valueOf(quizResult.getQuestions().get(0).getDifficulty()));
            resultCorrectAnswers.setText(quizResult.getCorrectAnswers() + "/" + quizResult.getQuestions().size());

            int stat = quizResult.getCorrectAnswers() * 100 / quizResult.getQuestions().size();
            resultPercent.setText(stat + " %");
        });
    }

    private void finishResult() {
        resultFinishButton.setOnClickListener(v -> finish());
    }
}
