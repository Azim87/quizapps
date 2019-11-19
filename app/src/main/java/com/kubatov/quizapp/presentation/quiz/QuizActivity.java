package com.kubatov.quizapp.presentation.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.R;
import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuizActivity extends AppCompatActivity implements QuizAdapter.OnItemClick {
    private QuizViewModel mQuizViewModel;

    public static final String SEEK_BAR = "seekbar";
    public static final String DIFF_DIFFICULT = "difficult";
    public static final String CATEG_ID = "category_id";
    public static final String CATEGORY_NAME = "category";


    @BindView(R.id.quiz_recycler_view)
    RecyclerView mQuizRecycler;
    private List<Questions> questionsArrayList = new ArrayList<>();
    private QuizAdapter mQuizAdapter;

    public static void start(Context context, int seekBarValue, int id, String category, String difficultValue) {
        Intent fakeIntent = new Intent(context, QuizActivity.class);
        fakeIntent.putExtra(SEEK_BAR, seekBarValue);
        fakeIntent.putExtra(CATEG_ID, id);
        fakeIntent.putExtra(CATEGORY_NAME, category);
        fakeIntent.putExtra(DIFF_DIFFICULT, difficultValue);
        context.startActivity(fakeIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        initRecycler();

    }

    private void initRecycler() {
        mQuizAdapter = new QuizAdapter(questionsArrayList, this);
        mQuizRecycler.setHasFixedSize(true);
        mQuizRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mQuizRecycler.setAdapter(mQuizAdapter);
        showQuizData();

    }

    private void showQuizData() {
        Intent fakeIntent = getIntent();
        int amount = fakeIntent.getIntExtra(SEEK_BAR, 0);
        int id = fakeIntent.getIntExtra(CATEG_ID, 0);
        String category = fakeIntent.getStringExtra(CATEGORY_NAME);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);
        getQuestionData(amount, category, difficulty);

        Log.d("ololo", "showQuizData: " + amount + " " + id + " " + difficulty);

    }

    private void getQuestionData(int amount, String category, String difficulty) {
        App.quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {

            @Override
            public void onSuccess(List<Questions> quizResponse) {
                questionsArrayList.addAll(quizResponse);
                mQuizAdapter.notifyDataSetChanged();
                Log.d("ololo", "onSuccess: " + quizResponse.get(0).getCategory());
            }

            @Override
            public void onFailure(String message) {
                Log.d("ololo", "onFailure: " + message);
            }
        });
    }

    @Override
    public void onClick(int questionPosition, int adapterPosition) {

    }
}
