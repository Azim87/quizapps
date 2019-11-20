package com.kubatov.quizapp.data.repository.remoteDataRep;

import com.kubatov.quizapp.data.repository.IQuizRepository;

public interface IQuizRemoteDataSource {

    void getQuestions(int amount, Integer category, String difficulty, IQuizRepository.OnQuizCallBack onQuizCallBack);
}