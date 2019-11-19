package com.kubatov.quizapp.data.repository;

import com.kubatov.quizapp.model.Category;
import com.kubatov.quizapp.model.Questions;

import java.util.List;

public interface IQuizRepository {

    void getQuizQuestions(int a, String c, String d, OnQuizCallBack questionCallBack);
    void getQuizCategory(int id, String name,  OnQuizCallBack categoryCallBack);

    interface OnQuizCallBack <T>{

        void onSuccess(List<T> quizResponse);

        void onFailure(String message);
    }
}
