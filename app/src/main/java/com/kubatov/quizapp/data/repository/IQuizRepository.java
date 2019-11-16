package com.kubatov.quizapp.data.repository;

import com.kubatov.quizapp.model.Questions;

import java.util.List;

public interface IQuizRepository {

    void getQuizData(int a, String c, String d, OnQuizCallBack onQuizCallBack);

    interface OnQuizCallBack {
        void onSuccess(List<Questions> quizQuestions);

        void onFailure(String message);
    }
}
