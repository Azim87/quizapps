package com.kubatov.quizapp.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnItemClickListener {
    private QuizViewModel mQuizViewModel;
    private QuizAdapter mQuizAdapter;

    public static final String SEEK_BAR = "amount";
    public static final String CATEGORY_NAME = "category";
    public static final String DIFF_DIFFICULT = "difficult";

    @BindView(R.id.quiz_recycler_view)
    RecyclerView mQuizRecycler;

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
        initRecycler();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initRecycler() {
        mQuizAdapter = new QuizAdapter(this);
        mQuizRecycler.setHasFixedSize(true);
        mQuizRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mQuizRecycler.setAdapter(mQuizAdapter);
        mQuizRecycler.setOnTouchListener((v, event) -> true);
        showQuizData();

    }

    private void showQuizData() {
        Intent fakeIntent = getIntent();
        int amount = fakeIntent.getIntExtra(SEEK_BAR, 0);
        int category = fakeIntent.getIntExtra(CATEGORY_NAME, 0);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);
        getQuestionData(amount, category, difficulty);
    }

    private void getQuestionData(Integer amount, Integer category, String difficulty) {

        mQuizViewModel.questions.observe(this, questions -> mQuizAdapter.setQuestions(questions));
        mQuizViewModel.initViews(amount, category, difficulty);
    }


    @Override
    public void onAnswerClick(int questionPosition, int answerPosition) {
        mQuizViewModel.onAnswerClick(questionPosition, answerPosition);
    }
}
