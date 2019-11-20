package com.kubatov.quizapp.presentation.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kubatov.quizapp.App;
import com.kubatov.quizapp.data.repository.IQuizRepository;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Questions>> questions = new MutableLiveData<>();



    void initViews(Integer amount, Integer category, String difficulty) {
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

    void onAnswerClick(int questionPosition, int answerPosition){

    }
}
