package com.kubatov.quizapp.presentation.quiz;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.core.SingleLiveEvent;
import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

import static com.kubatov.quizapp.presentation.quiz.QuizActivity.CATEGORY_NAME;
import static com.kubatov.quizapp.presentation.quiz.QuizActivity.DIFF_DIFFICULT;
import static com.kubatov.quizapp.presentation.quiz.QuizActivity.SEEK_BAR;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();


    void parseIntentData(Intent fakeIntent) {
        int amount = fakeIntent.getIntExtra(SEEK_BAR, 0);
        int category = fakeIntent.getIntExtra(CATEGORY_NAME, 0);
        String difficulty = fakeIntent.getStringExtra(DIFF_DIFFICULT);
        initViews(amount, category, difficulty);
    }

    private void initViews(Integer amount, Integer category, String difficulty) {
        currentQuestionPosition.setValue(0);
        App.quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Questions> quizResponse) {
                questions.setValue(quizResponse);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    void onAnswerClick(int questionPosition, int answerPosition) {

    }

    void onSkipButtonClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == questions.getValue().size() - 1) {
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition + 1);
            }
        }
    }
}
