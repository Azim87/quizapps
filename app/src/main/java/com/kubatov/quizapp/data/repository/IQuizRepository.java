package com.kubatov.quizapp.data.repository;

import com.kubatov.quizapp.data.pojo.QuizQuestions;

import java.util.List;

public interface IQuizRepository {

    void getQuizData();
    void setQuizData();


    interface OnQuizCallBack{
        void onSuccess(List<QuizQuestions> quizQuestions);
        void onFailure(Exception e);
    }
}
