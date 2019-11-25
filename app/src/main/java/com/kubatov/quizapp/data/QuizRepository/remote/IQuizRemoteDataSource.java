package com.kubatov.quizapp.data.QuizRepository.remote;

import com.kubatov.quizapp.data.QuizRepository.IQuizRepository;

public interface IQuizRemoteDataSource {

    void getQuestions(Integer amount, Integer category, String difficulty, IQuizRepository.OnQuizCallBack onQuizCallBack);
}