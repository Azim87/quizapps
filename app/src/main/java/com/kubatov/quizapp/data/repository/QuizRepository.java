package com.kubatov.quizapp.data.repository;


import androidx.annotation.Nullable;

import com.kubatov.quizapp.data.repository.remoteDataRep.QuizRemoteDataSource;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public class QuizRepository implements IQuizRepository {

    @Nullable
    private QuizRemoteDataSource dataSource;

    public QuizRepository(@Nullable QuizRemoteDataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void getQuizData(OnQuizCallBack onQuizCallBack) {
        dataSource.getQuestions(new OnQuizCallBack() {
            @Override
            public void onSuccess(List<Questions> quizQuestions) {
                onQuizCallBack.onSuccess(quizQuestions);
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    @Override
    public void setQuizData() {

    }
}
