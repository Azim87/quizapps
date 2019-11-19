package com.kubatov.quizapp.data.repository.remoteDataRep;

import com.kubatov.quizapp.data.repository.IQuizRepository;

public interface IQuizRemoteDataSource {

    void getQuestions(int amount, String category, String difficulty, IQuizRepository.OnQuizCallBack onQuizCallBack);

    void getCategory(int id, String name, IQuizRepository.OnQuizCategoryCallBack categoryQuizCallBack);
}

