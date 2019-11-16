package com.kubatov.quizapp.presentation.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.R;
import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizActivity extends AppCompatActivity {
    private QuizViewModel mQuizViewModel;

    public static final String SEEK_BAR = "seekbar";
    public static final String DIFF_CATEGORY = "category";
    public static final String DIFF_DIFFICULT = "difficult";

    @BindView(R.id.quiz_recycler_view)
    RecyclerView mQuizRecycler;

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
        ButterKnife.bind(this);
        showFakeData();
        initRecycler();
    }

    private void initRecycler() {
        QuizAdapter mQuizAdapter = new QuizAdapter();
        mQuizRecycler.setHasFixedSize(true);
        mQuizRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mQuizRecycler.setAdapter(mQuizAdapter);
    }

    private void showFakeData() {
        Intent fakeIntent = getIntent();
        int seekBar = fakeIntent.getIntExtra(SEEK_BAR, 0);
        String category = fakeIntent.getStringExtra(DIFF_CATEGORY);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);

        App.quizRepository.getQuizData(seekBar, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Questions> quizQuestions) {

                for (Questions questions: quizQuestions){
                Log.d("ololo", "onSuccess: " + questions.toString());
                }
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }
}
