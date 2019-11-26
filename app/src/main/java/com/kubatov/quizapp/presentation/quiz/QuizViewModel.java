package com.kubatov.quizapp.presentation.quiz;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.core.SingleLiveEvent;
import com.kubatov.quizapp.data.QuizRepository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizViewModel extends ViewModel {

    private IQuizRepository quizRepository;
    private List<Questions> questionsList;
    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> openResultEvent = new SingleLiveEvent<>();


    public void initViews(Integer amount, Integer category, String difficulty) {
        quizRepository = App.quizRepository;
        quizRepository.getQuizQuestions(amount, category, difficulty, new IQuizRepository.OnQuizCallBack() {
            @Override
            public void onSuccess(List<Questions> quizResponse) {
                questions.setValue(quizResponse);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(App.instance, "No internet", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void onAnswerClick(int questionPosition, int answerPosition) {

    }

    void onSkipButtonClick() {
        Integer currentPosition = currentQuestionPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition == questions.getValue().size() - 1) {
                openResultEvent.call();
                finishEvent.call();
            } else {
                currentQuestionPosition.setValue(currentPosition + 1);
            }
        }
    }

    void onPreviousQuestion() {
        if (currentQuestionPosition.getValue() > 0) {
            currentQuestionPosition.setValue(currentQuestionPosition.getValue() - 1);
        } else {
            finishEvent.call();
        }
    }
}
