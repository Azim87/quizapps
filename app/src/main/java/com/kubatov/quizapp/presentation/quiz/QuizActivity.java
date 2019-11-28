package com.kubatov.quizapp.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;
import com.kubatov.quizapp.presentation.result.ResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnItemClickListener {
    public static final String SEEK_BAR = "amount";
    public static final String CATEGORY_NAME = "category";
    public static final String DIFF_DIFFICULT = "difficult";

    private QuizViewModel mQuizViewModel;
    private QuizAdapter mQuizAdapter;

    @BindView(R.id.quiz_recycler_view)
    RecyclerView mQuizRecycler;
    @BindView(R.id.quiz_question_amount)
    TextView amountProgressView;
    @BindView(R.id.navigation)
    TextView categoryTextView;
    @BindView(R.id.quiz_progress_bar)
    ProgressBar amountProgressBar;
    @BindView(R.id.quiz_progress)
    ProgressBar quizProgressBar;
    @BindView(R.id.skip_button)
    Button skipButton;


    public static void start(Context context, int amount, int category, String difficultValue) {
        Intent fakeIntent = new Intent(context, QuizActivity.class);
        fakeIntent.putExtra(SEEK_BAR, amount);
        fakeIntent.putExtra(CATEGORY_NAME, category);
        fakeIntent.putExtra(DIFF_DIFFICULT, difficultValue);
        context.startActivity(fakeIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuizViewModel = ViewModelProviders.of(this)
                .get(QuizViewModel.class);

        ButterKnife.bind(this);
        initViewModel();
        getQuizData();
        amountProgressBar.setProgress(0);
        quizProgressBar.setVisibility(View.VISIBLE);
        skipButton.setVisibility(View.INVISIBLE);
    }

    private void getQuizData() {
        Intent intent = getIntent();
        int amount = intent.getIntExtra(SEEK_BAR, 5);
        int category = intent.getIntExtra(CATEGORY_NAME, 0);
        String difficulty = intent.getStringExtra(DIFF_DIFFICULT);
        mQuizViewModel.initViews(amount, category, difficulty);
    }

    @SuppressLint("SetTextI18n")
    private void initViewModel() {
        mQuizViewModel.mQuestions.observe(this, questions -> {
            mQuizAdapter.setQuestions(questions);
            amountProgressBar.setMax(questions.size());
            quizProgressBar.setVisibility(View.INVISIBLE);
            skipButton.setVisibility(View.VISIBLE);
        });

        mQuizViewModel.openResultEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer id) {
                ResultActivity.start(QuizActivity.this, id);
            }
        });

        mQuizViewModel.finishEvent.observe(this, aVoid -> finish());
        mQuizViewModel.currentQuestionPosition.observe(this, position -> {
            categoryTextView.setText(mQuizAdapter.getListPosition().get(position).getCategory());
            amountProgressView.setText(position + 1 + "/" + mQuizAdapter.getItemCount());
            amountProgressBar.setProgress(position + 1);
            mQuizRecycler.smoothScrollToPosition(position);

        });
        initRecycler();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initRecycler() {
        mQuizAdapter = new QuizAdapter(this);
        mQuizRecycler.setHasFixedSize(true);
        mQuizRecycler.setAdapter(mQuizAdapter);
        mQuizRecycler.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false));
        mQuizRecycler.setOnTouchListener((v, event) -> true);
    }

    @Override
    public void onAnswerClick(int questionPosition, int answerPosition) {
        mQuizViewModel.onAnswerClick(questionPosition, answerPosition);
    }

    @OnClick(R.id.skip_button)
    void onSkipClick(View view) {
        mQuizViewModel.onSkipButtonClick();
    }

    @OnClick(R.id.image_view_previous)
    void onBackPressed(View view) {
        mQuizViewModel.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        mQuizViewModel.onBackPressed();
    }
}
